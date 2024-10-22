package org.example.thirdwheel.stateholders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppStateHolder {

    // ======= Observable State Tracking Totals =======
    var totalIncome by mutableStateOf(0.0)
    var totalExpenses by mutableStateOf(0.0)
    var balance by mutableStateOf(0.0)

    var totalAssets by mutableStateOf(0.0)
    var totalNetWorth by mutableStateOf(0.0)
    var totalLiabilities by mutableStateOf(0.0)

    // ======= Data Lists =======
    val incomeList = mutableStateListOf<Pair<String, Double>>()
    val expenseList = mutableStateListOf<Pair<String, Double>>()
    val transactionsList = mutableStateListOf<Pair<String, Double>>()

    val expenseBudgets = mutableStateListOf<BudgetEntry>()

    val assetsList = mutableStateListOf<Pair<String, Double>>()
    val liabilitiesList = mutableStateListOf<Pair<String, Double>>()
    val savingsList = mutableStateListOf<Pair<String, Double>>()

    val allAccountsList = mutableStateListOf<String>() // Consolidated accounts list

    // ======= Data Models =======
    data class BudgetEntry(
        val categoryName: String,
        val budgetAmount: Double,
        var spentAmount: Double = 0.0 // Made mutable to update spentAmount
    )

    data class TransactionEntry(
        val categoryName: String,
        val amount: Double,
    )

    // ======= Public Functions =======

    fun addAssets(categoryName: String, amount: Double): Boolean {
        if (isAssetOrLiabilityDuplicate(categoryName)) return false
        assetsList.add(categoryName to amount)
        updateTotals()
        updateAllAccountsList()
        return true
    }

    fun addLiabilities(categoryName: String, amount: Double): Boolean {
        if (isAssetOrLiabilityDuplicate(categoryName)) return false
        liabilitiesList.add(categoryName to amount)
        updateTotals()
        updateAllAccountsList()
        return true
    }

    fun addSavings(categoryName: String, amount: Double): Boolean {
        if (savingsList.any { it.first.equals(categoryName, ignoreCase = true) }) return false
        savingsList.add(categoryName to amount)
        updateTotals()
        updateAllAccountsList()
        return true
    }

    fun addIncomeCategory(categoryName: String, amount: Double): Boolean {
        if (isCategoryDuplicate(categoryName)) return false
        incomeList.add(categoryName to amount)
        totalIncome += amount
        updateBalance()
        return true
    }

    fun addExpenseCategory(categoryName: String, budgetAmount: Double): Boolean {
        if (isCategoryDuplicate(categoryName)) return false
        expenseList.add(categoryName to 0.0)
        expenseBudgets.add(BudgetEntry(categoryName, budgetAmount))
        return true
    }

    fun deposit(accountName: String, amount: Double): Boolean {
        return updateAccountBalance(accountName, amount)
    }

    fun withdraw(accountName: String, amount: Double): Boolean {
        return updateAccountBalance(accountName, -amount)
    }

    fun transfer(fromAccount: String, toAccount: String, amount: Double): Boolean {
        if (withdraw(fromAccount, amount)) {
            return deposit(toAccount, amount)
        }
        return false
    }

    fun addTransaction(categoryName: String, transactionAmount: Double) {
        transactionsList.add(categoryName to transactionAmount)
        processTransaction(categoryName, transactionAmount)
        updateBalance()
    }

    // ======= Helper Methods =======

    private fun isCategoryDuplicate(categoryName: String): Boolean {
        return incomeList.any { it.first.equals(categoryName, ignoreCase = true) } ||
                expenseList.any { it.first.equals(categoryName, ignoreCase = true) }
    }

    private fun isAssetOrLiabilityDuplicate(categoryName: String): Boolean {
        return assetsList.any { it.first.equals(categoryName, ignoreCase = true) } ||
                liabilitiesList.any { it.first.equals(categoryName, ignoreCase = true) }
    }

    private fun updateAccountBalance(accountName: String, amount: Double): Boolean {
        val assetIndex = assetsList.indexOfFirst { it.first.equals(accountName, ignoreCase = true) }
        if (assetIndex != -1) {
            val (name, currentBalance) = assetsList[assetIndex]
            if (currentBalance + amount < 0) return false
            assetsList[assetIndex] = name to (currentBalance + amount)
            updateTotals()
            return true
        }

        val savingsIndex = savingsList.indexOfFirst { it.first.equals(accountName, ignoreCase = true) }
        if (savingsIndex != -1) {
            val (name, currentBalance) = savingsList[savingsIndex]
            if (currentBalance + amount < 0) return false
            savingsList[savingsIndex] = name to (currentBalance + amount)
            updateTotals()
            return true
        }

        val liabilityIndex = liabilitiesList.indexOfFirst { it.first.equals(accountName, ignoreCase = true) }
        if (liabilityIndex != -1) {
            val (name, currentBalance) = liabilitiesList[liabilityIndex]
            if (currentBalance + amount < 0) return false
            liabilitiesList[liabilityIndex] = name to (currentBalance + amount)
            updateTotals()
            return true
        }

        return false
    }

    private fun processTransaction(categoryName: String, amount: Double) {
        val expenseIndex = expenseList.indexOfFirst { it.first.equals(categoryName, ignoreCase = true) }
        if (expenseIndex != -1) {
            updateExpense(expenseIndex, amount)
            updateBudgetSpentAmount(categoryName, amount) // Update budget spent amount
        } else {
            val incomeIndex = incomeList.indexOfFirst { it.first.equals(categoryName, ignoreCase = true) }
            if (incomeIndex != -1) updateIncome(amount, incomeIndex)
        }
    }

    private fun updateExpense(expenseIndex: Int, amount: Double) {
        val (category, currentSpent) = expenseList[expenseIndex]
        expenseList[expenseIndex] = category to (currentSpent + amount)
        totalExpenses += amount
    }

    private fun updateIncome(amount: Double, incomeIndex: Int) {
        val (category, currentIncome) = incomeList[incomeIndex]
        incomeList[incomeIndex] = category to (currentIncome + amount)
        totalIncome += amount
    }

    private fun updateBudgetSpentAmount(categoryName: String, amount: Double) {
        val budgetIndex = expenseBudgets.indexOfFirst { it.categoryName.equals(categoryName, ignoreCase = true) }
        if (budgetIndex != -1) {
            val budgetEntry = expenseBudgets[budgetIndex]
            expenseBudgets[budgetIndex] = budgetEntry.copy(spentAmount = budgetEntry.spentAmount + amount)
        }
    }

    private fun updateTotals() {
        totalAssets = assetsList.sumOf { it.second } + savingsList.sumOf { it.second }
        totalLiabilities = liabilitiesList.sumOf { it.second }
        totalNetWorth = totalAssets - totalLiabilities
        updateBalance()
    }

    private fun updateBalance() {
        balance = totalIncome - totalExpenses
    }

    private fun updateAllAccountsList() {
        allAccountsList.clear()
        allAccountsList.addAll(assetsList.map { it.first })
        allAccountsList.addAll(savingsList.map { it.first })
        allAccountsList.addAll(liabilitiesList.map { it.first })
    }
}
