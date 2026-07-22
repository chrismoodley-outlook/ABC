package com.toti.baptist.connect.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.toti.baptist.connect.data.repositories.AuthRepository
import com.toti.baptist.connect.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val message: String) : AuthState()
    object SignedOut : AuthState()
    object ProfileSetupNeeded : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?> = _currentUser

    private val _needsProfileSetup = MutableLiveData<Boolean>()
    val needsProfileSetup: LiveData<Boolean> = _needsProfileSetup

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        val user = authRepository.getCurrentUser()
        _currentUser.value = user
        
        if (user != null) {
            // Check if profile is complete
            viewModelScope.launch {
                try {
                    val isComplete = userRepository.checkIfProfileComplete(user.uid)
                    _needsProfileSetup.value = !isComplete
                    _authState.value = if (isComplete) {
                        AuthState.Success(user)
                    } else {
                        AuthState.ProfileSetupNeeded
                    }
                } catch (e: Exception) {
                    _authState.value = AuthState.Success(user) // Proceed even if check fails
                }
            }
        } else {
            _authState.value = AuthState.SignedOut
        }
    }

    fun loginWithEmail(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val user = authRepository.loginWithEmail(email, password)
                _currentUser.value = user
                
                // Check if profile needs setup
                val isComplete = userRepository.checkIfProfileComplete(user.uid)
                _needsProfileSetup.value = !isComplete
                
                _authState.value = if (isComplete) {
                    AuthState.Success(user)
                } else {
                    AuthState.ProfileSetupNeeded
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun registerWithEmail(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val user = authRepository.registerWithEmail(email, password)
                _currentUser.value = user
                
                // Create user profile
                userRepository.createUserProfile(user.uid, email)
                _needsProfileSetup.value = true
                _authState.value = AuthState.ProfileSetupNeeded
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun loginWithGoogle(idToken: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val user = authRepository.loginWithGoogle(idToken)
                _currentUser.value = user
                
                // Check if profile exists, if not create it
                val exists = userRepository.checkIfProfileExists(user.uid)
                if (!exists) {
                    userRepository.createUserProfile(user.uid, user.email ?: "")
                    _needsProfileSetup.value = true
                    _authState.value = AuthState.ProfileSetupNeeded
                } else {
                    val isComplete = userRepository.checkIfProfileComplete(user.uid)
                    _needsProfileSetup.value = !isComplete
                    _authState.value = if (isComplete) {
                        AuthState.Success(user)
                    } else {
                        AuthState.ProfileSetupNeeded
                    }
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Google Sign-In failed")
            }
        }
    }

    fun resetPassword(email: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                authRepository.resetPassword(email)
                _authState.value = AuthState.Success(FirebaseAuth.getInstance().currentUser ?: return@launch)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Password reset failed")
            }
        }
    }

    fun logout() {
        authRepository.logout()
        _currentUser.value = null
        _needsProfileSetup.value = false
        _authState.value = AuthState.SignedOut
    }

    fun isUserLoggedIn(): Boolean {
        return _currentUser.value != null
    }
}
