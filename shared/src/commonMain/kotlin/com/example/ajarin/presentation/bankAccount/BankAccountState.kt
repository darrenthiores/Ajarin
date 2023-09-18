package com.example.ajarin.presentation.bankAccount

data class BankAccountState(
    val bankAccounts: List<BankAccount> = emptyList(),
    val bankAccountsLoading: Boolean = false,
    val bankAccountsError: Error? = null
)

data class BankAccount(
    val id: String,
    val name: String,
    val accountNumber: String,
    val bank: Bank
)

data class Bank(
    val bankId: String,
    val bankName: String
)

val dummyBanks = listOf(
    Bank(
        bankId = "1",
        bankName = "BCA"
    ),
    Bank(
        bankId = "2",
        bankName = "BNI"
    ),
    Bank(
        bankId = "3",
        bankName = "BRI"
    ),
    Bank(
        bankId = "4",
        bankName = "Mandiri"
    ),
    Bank(
        bankId = "5",
        bankName = "BSI"
    ),
    Bank(
        bankId = "6",
        bankName = "Commbank"
    )
)

val dummyAccount = listOf(
    BankAccount(
        id = "0",
        name = "Darren",
        accountNumber = "021017000",
        bank = dummyBanks[0]
    ),
    BankAccount(
        id = "1",
        name = "Darren",
        accountNumber = "021017000",
        bank = dummyBanks[1]
    ),
    BankAccount(
        id = "2",
        name = "Darren",
        accountNumber = "021017000",
        bank = dummyBanks[2]
    )
)