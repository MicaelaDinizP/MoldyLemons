# Moldy Lemons

**Android App for Movie & Series Loving Couples** 🍿🍋

**Repository:** [github.com/MicaelaDinizP/MoldyLemons](https://github.com/MicaelaDinizP/MoldyLemons)

---

## 📖 About The Project

Moldy Lemons is a personal Android project designed to help couples share a joint account to track what they watch together—movies, series, and anime. Users can:

- Register watched content and define interests on a wishlist
- Write and read reviews with **ratings** (1–5) and **emoji reactions**
- Browse a **Review List** powered by LiveData observers
- Manage session state (login/logout) via a dedicated `SessionManager`

This project serves as a playground for practicing **clean architecture**, **java/android programming**, **automated testing**, and **project organization**.

---

## 🔧 Built With

- **Java** (Android SDK)
- **Room Database** (abstraction over SQLite)
- **MVVM** architecture with **LiveData** / **Observers**
- **JUnit 5** for unit testing
- **GitHub Issues** & **semantic commits** for project management

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Flamingo or later
- Java 8

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MicaelaDinizP/MoldyLemons.git
   ```
2. Open in Android Studio and let it sync Gradle.
3. Build and run on an emulator or physical device (min SDK 26).

---

## ⚙️ Usage (so far)

1. **Register/Log in** with test credentials (e.g., `user` / `1234`).
2. **Browse** the main feed to see tracked content.

---

## 🌟 Features

- **Joint Account**: Single profile for both partners.
- **Media Models**: `Media` base class extended by `Movie`, `Series`, `Anime`.
- **Genres & Demographics**: Enums enforce valid genres and anime-specific demographics.
- **Dynamic UI**: LiveData observers update lists in real time.
- **Validation**: Model-level checks (title, genres, rating range, emoji, duration).
- **Testing**: Unit tests for model validations and repository logic.
- **Project Organization**: GitHub Issues, branches per feature, semantic commit messages.

---

## 📂 Project Structure

```text
devandroid.micaela.moldylemons
│
├── data
│   ├── model
│   │   ├── Media.java
│   │   ├── Movie.java
│   │   ├── Serie.java
│   │   ├── Anime.java
│   │   ├── Review.java
│   │   ├── Couple.java
│   │   └── enums
│   │       ├── MediaType.java
│   │       ├── Genre.java
│   │       └── Demographic.java
│   │
│   ├── local
│   │   ├── AppDatabase.java
│   │   └── CoupleDAO.java
│   │   └── DateConverter.java
│   │
│   └── repository
│       └── CoupleRepository.java
│
├── ui
│   ├── feed
│   │   ├── FeedFragment.java
│   │   └── FeedViewModel.java
│   │
│   ├── reviewlist
│   │   ├── ReviewListFragment.java
│   │   └── ReviewListViewModel.java
│   │
│   └── wishlist
│   │   ├── WishlistFragment.java
│   │   └── WishlistViewModel.java
│   │ 
│   ├── login
│       ├── LoginActivity.java
│       └── LoginViewModel.java 
│   
│
├── util
│   ├── FakeData.java
│   └── SessionManager.java
│
└── MainActivity.java

```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch: `feature/your-feature-name`.
3. Commit your changes using semantic prefixes (`feat:`, `fix:`, `test:`).
4. Open a pull request referencing the related issue.

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---

## ✒️ Authors

- **Micaela Diniz** – _Initial work_ – [MicaelaDinizP](https://github.com/MicaelaDinizP) (www.linkedin.com/in/micaela-paes-12397022b)

---

## 🚀 Future Plans

- Integrate **SonarQube** for static code analysis.
- Add **instrumentation tests** with Espresso.
- Implement **API synchronization** and cloud backup.
- Polish UI/UX with Material 3 components.

