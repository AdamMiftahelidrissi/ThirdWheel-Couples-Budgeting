# BudgetApp - Compose Multiplatform

![BudgetApp Logo](./images/budgetapp_logo.png) <!-- Add a logo if available -->

## Overview
**BudgetApp** is a cross-platform budgeting application built using **Compose Multiplatform** with **Jetpack Compose**. It offers a **unified user interface** for both Android and iOS, focusing on a **minimalistic** and **user-friendly** design. The app helps users manage personal finances efficiently by tracking **income, expenses, balances**, and **budgets**.

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Code Overview](#code-overview)
- [Technologies Used](#technologies-used)

## Features
BudgetApp provides the following core features:
- **Cross-platform Compatibility**: Single codebase for a consistent user experience on both Android and iOS.
- **Minimalistic UI**: A clean and intuitive interface that simplifies financial management.
- **User Account Management**: Users can create accounts, log in, and reset passwords.
- **Comprehensive Financial Tracking**:
  - **Home Screen**: Displays total income, expenses, and net balance, with options to add categories and transactions.
  - **Transactions Screen**: Lists all transactions sorted by date.
  - **Balances Screen**: Manages assets, liabilities, and savings accounts, along with net worth calculation.
  - **Budget Screen**: Provides a visual overview of budget utilization with color-coded progress bars.
- **Category and Budget Creation**:
  - Add **income** and **expense** categories.
  - Set budgets for expense categories with visual indicators that change from teal to yellow to red as spending approaches the limit.
- **Account Customization**:
  - Supports various account types under assets (e.g., debit cards, real estate), liabilities (e.g., credit cards, loans), and savings (e.g., retirement, vacation).

## Screenshots

The following screenshots demonstrate BudgetApp's functionality on **iOS**, highlighting its cross-platform design:

### User Authentication
#### Login Screen
![Login Screen](./ThirdWheel%20Screenshots/Login/Login%20Screen.png)
*The user login page, allowing access to the app.*

#### Create Account Screen
![Create Account Screen](./ThirdWheel%20Screenshots/Login/Create%20Account%20Screen.png)
*Screen for new account registration.*

#### Forgot Password Screen
![Forgot Password Screen](./ThirdWheel%20Screenshots/Login/Forgot%20Password%20Screen.png)
*Password recovery interface.*

### Home Screen
![Home Screen](./ThirdWheel%20Screenshots/Home/Home%20Screen.png)
*Displays an overview of total income, expenses, and net balance.*

### Adding Categories
#### Add Expense Category
![Add Expense Category](./ThirdWheel%20Screenshots/Home/Add%20Expense%20Category.png)
*UI for adding an expense category, accessible via the floating action button, with an option to set a budget.*

#### Add Income Category
![Add Income Category](./ThirdWheel%20Screenshots/Home/Add%20Income%20Category.png)
*UI for adding an income category.*

### Transactions
#### Adding Transactions
![Add Food Transaction](./ThirdWheel%20Screenshots/Home/Add%20Food%20Transaction.png)
*Adding a transaction to the "Food" category by clicking on it.*

#### Updated Transactions
![Transactions Added](./ThirdWheel%20Screenshots/Transactions/Transactions%20Added.png)
*Transaction screen updated with new entries.*

### Account Management
#### Balances Screen
![Balances Screen](./ThirdWheel%20Screenshots/Balances/Balances%20Screen.png)
*Overview of assets, liabilities, and savings, with net worth calculation.*

#### Add Account
![Add Account](./ThirdWheel%20Screenshots/Balances/Add%20Account.png)
*UI for adding a new account via the floating action button.*

#### Cash Account Added
![Cash Account Added](./ThirdWheel%20Screenshots/Balances/Cash%20Account%20Added.png)
*Cash account displayed after creation.*

#### Transfer Between Accounts
![Transfer UI](./ThirdWheel%20Screenshots/Balances/Transfer.png)
*Interface for transferring funds between accounts.*

### Budget Management
#### Budget Overview
![Budget Screen](./ThirdWheel%20Screenshots/Budget/Budget%20Screen.png)
*Visual representation of budget utilization with progress bars.*

#### Budget Progress
![Food Budget Updated](./ThirdWheel%20Screenshots/Budget/Food%20Budget%20Updated.png)
*Progress bar updating after a transaction is made in the "Food" category.*

## Code Overview
The app is developed using **Compose Multiplatform**, allowing a shared codebase for both Android and iOS. Key components include:
- **HomeScreen.kt**: Manages income, expenses, and balance display, and handles category/transaction creation.
- **TransactionsScreen.kt**: Lists all transactions and manages transaction data flow.
- **BalancesScreen.kt**: Handles account management and net worth calculation.
- **BudgetScreen.kt**: Visualizes budget progress and tracks spending limits.

## Technologies Used
- **Compose Multiplatform** - Enables a shared UI for Android and iOS.
- **Jetpack Compose** - Used for building UI components and managing navigation.
- **Kotlin** - Primary programming language for the app.
- **State Management** - Manages app data flow and UI state.
- **Coroutines** - Handles asynchronous operations for smooth performance.

