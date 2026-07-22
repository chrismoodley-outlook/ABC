# TotiBaptistConnect.Mobile

Android church app for Toti Baptist Connect

## Overview

TotiBaptistConnect is a modern Android mobile application built with Kotlin and Jetpack Compose. It provides church members with easy access to events, messages, and community features.

## Project Structure

```
app/
├── src/main/
│   ├── java/com/toti/baptist/connect/
│   │   ├── MainActivity.kt                 # Main entry point
│   │   ├── data/
│   │   │   ├── api/
│   │   │   │   └── ApiService.kt          # Retrofit API interface
│   │   │   └── models/
│   │   │       ├── Event.kt               # Event data model
│   │   │       └── User.kt                # User data model
│   │   └── ui/
│   │       ├── screens/
│   │       │   └── HomeScreen.kt          # Home screen composable
│   │       └── theme/
│   │           ├── Theme.kt               # Material3 theme
│   │           └── Type.kt                # Typography definitions
│   ├── res/
│   │   ├── values/
│   │   │   ├── colors.xml                 # Color definitions
│   │   │   ├── strings.xml                # String resources
│   │   │   └── themes.xml                 # Theme resources
│   │   └── mipmap/                        # App icons
│   └── AndroidManifest.xml                # App manifest
├── build.gradle.kts                       # App-level build config
└── proguard-rules.pro                     # Code obfuscation rules
```

## Technology Stack

- **Language:** Kotlin 1.9.0
- **UI Framework:** Jetpack Compose
- **Minimum SDK:** Android 7.0 (API 24)
- **Target SDK:** Android 14 (API 34)
- **Build System:** Gradle with Kotlin DSL
- **Architecture:** MVVM with Compose

## Dependencies

### Core Android
- AndroidX Core KTX
- AndroidX AppCompat
- Lifecycle Runtime

### UI & Compose
- Jetpack Compose 1.5.4
- Material3 Design System
- Activity Compose

### Networking
- Retrofit 2.9.0
- OkHttp 4.11.0
- GSON 2.10.1

### Dependency Injection
- Koin 3.4.0

### Image Loading
- Coil for Compose 2.4.0

### Testing
- JUnit 4
- Espresso
- Compose Test

## Getting Started

### Prerequisites
- Android Studio (Latest version)
- Java 8 or higher
- Android SDK 34

### Building the Project

1. Clone the repository
```bash
git clone https://github.com/chrismoodley-outlook/ABC.git
cd ABC
```

2. Build the project
```bash
./gradlew build
```

3. Run on emulator or device
```bash
./gradlew installDebug
```

### Running Tests
```bash
./gradlew test
```

## Features (Planned)

- [ ] Home screen with church updates
- [ ] Event listing and details
- [ ] Direct messaging with community members
- [ ] Prayer request board
- [ ] Service live streaming
- [ ] Donation/giving portal
- [ ] User profile management
- [ ] Notifications

## Development

### Code Style
- Follow Kotlin conventions
- Use descriptive variable and function names
- Add comments for complex logic

### Architecture
- MVVM pattern with Compose
- Repository pattern for data access
- Dependency injection with Koin
- Separation of concerns (UI, Data, Domain layers)

## Future Improvements

- [ ] Implement MVVM ViewModel layer
- [ ] Add local database (Room)
- [ ] Implement authentication
- [ ] Add push notifications
- [ ] Create feature modules
- [ ] Add comprehensive unit tests
- [ ] Implement Hilt for advanced DI

## License

This project is private and owned by Toti Baptist Connect.

## Support

For issues or feature requests, please contact the development team.
