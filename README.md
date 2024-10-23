# BudgetApp - Compose Multiplatform

## Overview
**BudgetApp** is a cross-platform budgeting application built using **Compose Multiplatform** with **Jetpack Compose** as the UI framework. It offers a **unified user interface** for both Android and iOS, focusing on a **minimalistic** and **user-friendly** design. The app allows users to manage personal finances efficiently, tracking **income, expenses, balances**, and **budgets**.

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Code Overview](#code-overview)
- [Technologies Used](#technologies-used)

## Features
BudgetApp offers the following core features:
- **Cross-platform Compatibility**: Built with a single codebase, the app ensures a seamless user experience on both Android and iOS.
- **Clean, Minimalistic Design**: The intuitive interface makes managing finances simple and straightforward.
- **User Account Management**: Users can create accounts, log in, and recover passwords.
- **Comprehensive Financial Tracking**:
  - **Home Screen**: Provides a quick overview of total income, expenses, and net balance. Users can add categories and transactions here.
  - **Transactions Screen**: Lists all transactions, sorted by date.
  - **Balances Screen**: Displays accounts for assets, liabilities, and savings, along with a net worth overview.
  - **Budget Screen**: Shows budget utilization with progress bars that change colors based on spending levels.
- **Category and Budget Creation**:
  - Users can add **income** and **expense** categories.
  - Set budgets for expense categories, with visual indicators that change from teal to yellow to red as spending approaches the limit.
- **Account Customization**:
  - Add account types under assets (e.g., debit cards, real estate), liabilities (e.g., credit cards, loans), and savings (e.g., retirement, vacation).

## Screenshots

Below are screenshots of the app running on **iOS**, demonstrating its cross-platform design:

### Login Screen
![Login Screen](./ThirdWheel%20Screenshots/Login/Login%20Screen.png)
*User login page.*

### Create Account Screen
![Create Account Screen](./ThirdWheel%20Screenshots/Login/Create%20Account%20Screen.png)
*Screen for creating a new account.*

### Forgot Password Screen
![Forgot Password Screen](./ThirdWheel%20Screenshots/Login/Forgot%20Password%20Screen.png)
*Password recovery screen.*

### Home Screen
![Home Screen](./ThirdWheel%20Screenshots/Home/Home%20Screen.png)
*Overview of total income, expenses, and balance.*

### Add Expense Category
![Add Expense Category](./ThirdWheel%20Screenshots/Home/Add%20Expense%20Category.png)
*UI for adding an expense category, accessible via the floating action button. Allows setting a budget.*

### Add Income Category
![Add Income Category](./ThirdWheel%20Screenshots/Home/Add%20Income%20Category.png)
*UI for adding an income category.*

### Categories Added
![Categories Added](./ThirdWheel%20Screenshots/Home/After%20Categories%20Added.png)
*Home screen after categories have been added.*

### Add Food Transaction
![Add Food Transaction](./ThirdWheel%20Screenshots/Home/Add%20Food%20Transaction.png)
*Adding a transaction to the Food category by clicking on it.*

### Add Salary Transaction
![Add Salary Transaction](./ThirdWheel%20Screenshots/Home/Add%20Salary%20Transaction.png)
*Adding a transaction to the Salary category.*

### After Transactions Added
![After Transactions Added](./ThirdWheel%20Screenshots/Home/After%20Transactions%20Added.png)
*Home screen updated after transactions have been added.*

### Clothes Expense Added
![Clothes Expense Added](./ThirdWheel%20Screenshots/Home/Clothes%20Expense%20Added.png)
*Adding a Clothes category and transactions to it.*

### Transactions Screen
![Transactions Screen](./ThirdWheel%20Screenshots/Transactions/Transactions%20Screen.png)
*Displays all transactions made.*

### Transactions Added
![Transactions Added](./ThirdWheel%20Screenshots/Transactions/Transactions%20Added.png)
*Updates to the screen as transactions are made in their respective categories.*

### Balances Screen
![Balances Screen](./ThirdWheel%20Screenshots/Balances/Balances%20Screen.png)
*Overview of assets, liabilities, and savings, with net worth calculation.*

### Add Account
![Add Account](./ThirdWheel%20Screenshots/Balances/Add%20Account.png)
*UI for adding an account, accessible via the floating action button.*

### Cash Account Added
![Cash Account Added](./ThirdWheel%20Screenshots/Balances/Cash%20Account%20Added.png)
*Cash account displayed after creation.*

### All Accounts Added
![All Accounts Added](./ThirdWheel%20Screenshots/Balances/All%20Accounts%20Added.png)
*Multiple accounts added to the Balances Screen.*

### Deposit or Withdraw
![Deposit or Withdraw](./ThirdWheel%20Screenshots/Balances/Deposit%20or%20Withdraw.png)
*Deposit or withdraw UI accessed by clicking on an account.*

### Transfer UI
![Transfer UI](./ThirdWheel%20Screenshots/Balances/Transfer.png)
*UI for transferring funds between accounts.*

### Transfer Dropdown
![Transfer Dropdown](./ThirdWheel%20Screenshots/Balances/Transfer%20Dropdown.png)
*Dropdown for selecting accounts during a transfer.*

### Updated Balances
![Updated Balances](./ThirdWheel%20Screenshots/Balances/Accounts%20Updated.png)
*Balances screen updated after a transfer.*

### Budget Screen
![Budget Screen](./images/budget_screen.png)
*Visual representation of budget utilization, with color-coded progress bars.*

## Code Overview
BudgetApp is developed using **Compose Multiplatform**, enabling a shared codebase across Android and iOS. Key components include:
- **HomeScreen.kt**: Displays income, expenses, and balance, and manages category/transaction creation.
- **TransactionsScreen.kt**: Lists all transactions and manages their data flow.
- **BalancesScreen.kt**: Manages accounts and displays net worth calculation.
- **BudgetScreen.kt**: Visualizes budget progress and spending limits.

## Technologies Used
- **Compose Multiplatform** - For building the cross-platform UI.
- **Jetpack Compose** - For UI development and navigation.
- **Kotlin** - The programming language for the app.
- **State Management** - For handling data flow and UI state.
- **Coroutines** - For asynchronous operations.

