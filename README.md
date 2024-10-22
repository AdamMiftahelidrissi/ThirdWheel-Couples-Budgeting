# BudgetApp - Compose Multiplatform

![BudgetApp Logo](./images/budgetapp_logo.png) <!-- Add a logo if available -->

## Overview
**BudgetApp** is a cross-platform budgeting application built using **Compose Multiplatform** with **Jetpack Compose** as the UI framework. It offers a **unified user interface** for both Android and iOS, designed to be **minimalistic** and **user-friendly**. This app allows users to easily manage personal finances by tracking **income, expenses, balances**, and **budgets**.

## Table of Contents
- [Features](#features)
- [Screenshots](#screenshots)
- [Code Overview](#code-overview)
- [Technologies Used](#technologies-used)

## Features
BudgetApp is built with the following features:
- **Cross-platform Compatibility**: Single codebase for both Android and iOS, providing a seamless experience across platforms.
- **Clean, Minimalistic Design**: Intuitive interface that makes managing finances straightforward.
- **User Account Management**: Allows users to create accounts, log in, and recover passwords if needed.
- **Comprehensive Financial Tracking**: 
  - **Home Screen**: Offers a quick overview of total income, expenses, and net balance. Users can add categories and transactions here.
  - **Transactions Screen**: Lists all transactions created in the Home Screen, sorted by date.
  - **Balances Screen**: Displays added accounts (assets, liabilities, and savings), along with a net worth overview.
  - **Budget Screen**: Visual progress bars represent budget utilization, changing color as spending approaches limits.
- **Category and Budget Creation**:
  - Add income and expense categories. 
  - Set budgets for expense categories, with visual indicators that show spending progress in teal, yellow, and red.
- **Account Customization**:
  - Add various account types under assets (e.g., debit cards, real estate), liabilities (e.g., credit cards, loans), and savings (e.g., retirement, vacation).

## Screenshots

BudgetApp's UI is consistent across platforms. Below are screenshots from the **iOS version**, highlighting the app’s multiplatform functionality:

### Login Screen
![Login Screen](./ThirdWheel%20Screenshots/Login/Login%20Screen.png)
*Page for user login.*

### Create Account Screen
![Create Account Screen](./ThirdWheel%20Screenshots/Login/Create%20Account%20Screen.png)
*Screen for creating a new account.*

### Forgot Password Screen
![Forgot Password Screen](./ThirdWheel%20Screenshots/Login/Forgot%20Password%20Screen.png)
*Allows users to reset their password.*

### Home Screen
![Home Screen](./ThirdWheel%20Screenshots/Home/Home%20Screen.png)
*Main screen displaying total income, expenses, and balance. Users can add categories and transactions.*

### Add Expense Category
![Add Expense Category](./ThirdWheel%20Screenshots/Home/Add%20Expense%20Category.png)
*UI for adding an expense category, including fields to set a budget.*

### Add Income Category
![Add Income Category](./ThirdWheel%20Screenshots/Home/Add%20Income%20Category.png)
*UI for adding an income category.*

### After Categories Added
![After Categories Added](./ThirdWheel%20Screenshots/Home/After%20Categories%20Added.png)
*Home screen after categories have been added.*

### Add Food Transaction
![Add Food Transaction](./ThirdWheel%20Screenshots/Home/Add%20Food%20Transaction.png)
*Adding a transaction to the Food category.*

### Add Salary Transaction
![Add Salary Transaction](./ThirdWheel%20Screenshots/Home/Add%20Salary%20Transaction.png)
*Adding a transaction to the Salary category.*

### After Transactions Added
![After Transactions Added](./ThirdWheel%20Screenshots/Home/After%20Transactions%20Added.png)
*Updated Home screen after transactions have been made.*

### Clothes Expense Added
![Clothes Expense Added](./ThirdWheel%20Screenshots/Home/Clothes%20Expense%20Added.png)
*Adding a Clothes category and adding a transaction to it.*

### Transactions Screen
![Transaction Screen](./images/transaction_screen.png)
*Displays all user transactions.*

### Budget Screen
![Budget Screen](./images/budget_screen.png)
*Visual representation of budget utilization, with progress bars showing spending relative to limits.*

### Balances Screen
![Balances Screen](./images/balances_screen.png)
*Shows the overview of assets, liabilities, and savings, along with net worth calculation.*

## Code Overview
The app is built with **Compose Multiplatform**, ensuring code reuse for UI across Android and iOS. Here's an overview of the key components:
- **HomeScreen.kt**: Manages the display of income, expenses, and balance.
- **BudgetScreen.kt**: Handles budget visualization and progress indicators.
- **TransactionsScreen.kt**: Lists all transactions made.
- **BalancesScreen.kt**: Displays account overview and manages different account types.

## Technologies Used
- **Compose Multiplatform** - For building the cross-platform UI.
- **Jetpack Compose** - For UI components and navigation.
- **Kotlin** - Primary programming language for the project.
- **State Management** - For handling state and data flow in the app.
- **Coroutines** - For asynchronous operations.

