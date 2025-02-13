# ThirdWheel: Couples Budgeting - Compose Multiplatform

## Overview
**BudgetApp** is a modern, cross-platform budgeting application built with **Compose Multiplatform** using **Jetpack Compose**. It provides a seamless **unified user interface** across both Android and iOS devices. Designed to be **minimalistic** and **user-friendly**, BudgetApp allows users to efficiently track **income, expenses, balances**, and **budgets**, making personal financial management simple and straightforward.

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Technologies Used](#technologies-used)

## Features
BudgetApp boasts a robust set of features that ensure a comprehensive and user-centric financial tracking experience:
- **Cross-platform Compatibility**: A single codebase for a consistent experience across Android and iOS.
- **Minimalistic UI**: Clean, intuitive design that emphasizes simplicity and clarity.
- **User Account Management**: Supports secure account creation, login, and password recovery.
- **Comprehensive Financial Tracking**:
  - **Home Screen**: An overview of total income, expenses, and net balance, with the ability to add categories and transactions.
  - **Transactions Screen**: Displays all transactions in a chronological list.
  - **Balances Screen**: Offers detailed management of assets, liabilities, and savings accounts, along with net worth calculation.
  - **Budget Screen**: Features dynamic progress bars that provide a visual overview of budget utilization with color-coded indicators.
- **Category and Budget Creation**:
  - Create and manage **income** and **expense** categories.
  - Set budgets for expense categories with visual alerts that shift from teal to yellow to red as spending nears limits.
- **Account Customization**:
  - Supports diverse account types, such as assets (e.g., debit cards, real estate), liabilities (e.g., credit cards, loans), and savings (e.g., retirement funds, vacation savings).

## Screenshots

Here are the screenshots demonstrating BudgetApp's functionality on **iOS**, highlighting its cross-platform design and user-friendly interface:

### User Authentication
#### Login Screen
![Login Screen](./ThirdWheel%20Screenshots/Login/Login%20Screen.png)
*The login page for user access to the app.*

#### Create Account Screen
![Create Account Screen](./ThirdWheel%20Screenshots/Login/Create%20Account%20Screen.png)
*Screen for new account registration.*

#### Forgot Password Screen
![Forgot Password Screen](./ThirdWheel%20Screenshots/Login/Forgot%20Password%20Screen.png)
*Password recovery screen.*

### Home Screen
![Home Screen](./ThirdWheel%20Screenshots/Home/Home%20Screen.png)
*Provides an overview of total income, expenses, and net balance.*

### Adding Categories
#### Add Expense Category
![Add Expense Category](./ThirdWheel%20Screenshots/Home/Add%20Expense%20Category.png)
*Interface for adding a new expense category, accessible via the floating action button, with options to set a budget.*

#### Add Income Category
![Add Income Category](./ThirdWheel%20Screenshots/Home/Add%20Income%20Category.png)
*Interface for adding a new income category.*

### Transactions
#### Adding Transactions
![Add Food Transaction](./ThirdWheel%20Screenshots/Home/Add%20Food%20Transaction.png)
*Adding a transaction to the "Food" category by clicking on it.*

#### Updated Transactions
![Transactions Added](./ThirdWheel%20Screenshots/Transactions/Transactions%20Added.png)
*Screen updated with new transactions added to the list.*

### Account Management
#### Balances Screen
![Balances Screen](./ThirdWheel%20Screenshots/Balances/Balances%20Screen.png)
*Displays an overview of assets, liabilities, and savings, along with net worth calculation.*

#### Add Account
![Add Account](./ThirdWheel%20Screenshots/Balances/Add%20Account.png)
*Interface for adding a new account via the floating action button.*

#### Cash Account Added
![Cash Account Added](./ThirdWheel%20Screenshots/Balances/Cash%20Account%20Added.png)
*Newly created cash account shown in the balances screen.*

#### Transfer Between Accounts
![Transfer UI](./ThirdWheel%20Screenshots/Balances/Transfer.png)
*User interface for transferring funds between accounts.*

### Budget Management
#### Budget Overview
![Budget Screen](./ThirdWheel%20Screenshots/Budget/Budget%20Screen.png)
*Visual representation of budget utilization, with progress bars indicating spending relative to limits.*

#### Budget Progress
![Food Budget Updated](./ThirdWheel%20Screenshots/Budget/Food%20Budget%20Updated.png)
*Progress bar updating after a transaction is made in the "Food" category.*

#### Clothes Budget Added
![Clothes Budget Updated](./ThirdWheel%20Screenshots/Budget/Clothes%20Budget%20Updated.png)
*Budget added for the "Clothes" category, with progress bar tracking expenses.*

## Technologies Used
- **Compose Multiplatform** - Enables a shared UI across Android and iOS.
- **Jetpack Compose** - Used for building the user interface and managing navigation.
- **Kotlin** - The primary programming language for the app.
- **State Management** - Efficiently manages data flow and UI state across components.
- **Coroutines** - Facilitates asynchronous operations for seamless performance.
- **Firebase Authentication** - Provides secure authentication for users.
- **Firebase Firestore** - A NoSQL cloud database for real-time data storage and syncing.

