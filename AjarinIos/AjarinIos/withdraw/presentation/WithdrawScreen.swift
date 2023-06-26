//
//  WithdrawScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct WithdrawScreen: View {
    @StateObject private var viewModel = IosWithdrawViewModel()
    @State private var accountSheetOpen = false
    @Environment(\.dismiss) var dismiss
    
    let formatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.numberStyle = .decimal
        formatter.groupingSeparator = ","
        return formatter
    }()
    
    var body: some View {
        VStack {
            List {
                Section("Bank Selection") {
                    BankField(
                        value: viewModel.state.selectedAccount,
                        title: "Select Account",
                        onClick: {
                            accountSheetOpen = true
                        }
                    )
                }
                .listRowSeparator(.hidden)
                
                Divider()
                    .listRowSeparator(.hidden)
                
                Section("SEND AMOUNT") {
                    TextField(
                        "Rp0",
                        text: Binding(
                            get: {
                                if viewModel.state.amount.isEmpty {
                                    return ""
                                } else {
                                    let text = formatter.string(
                                        for: Int(viewModel.state.amount)
                                    ) ?? ""
                                    
                                    return "Rp\(text)"
                                }
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnAmountChange(
                                        amount: $0
                                            .replacingOccurrences(
                                                of: ",",
                                                with: ""
                                            )
                                            .replacingOccurrences(
                                                of: "Rp",
                                                with: ""
                                            )
                                    )
                                )
                            }
                        )
                    )
                    .font(.title)
                    .keyboardType(.decimalPad)
                    
                    if let amountError = viewModel.state.amountError {
                        HStack {
                            Text(amountError.description())
                                .font(.caption)
                                .foregroundColor(.red)
                            
                            Spacer()
                        }
                    }
                    
                    DefaultTextField(
                        title: "Write a Note",
                        text: Binding(
                            get: {
                                viewModel.state.note
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnNoteChange(note: $0)
                                )
                            }
                        )
                    )
                }
                .listRowSeparator(.hidden)
            }
            .listStyle(.plain)
            
            Spacer()
            
            Button {
                viewModel.onEvent(
                    event: .Withdraw()
                )
            } label: {
                Spacer()
                
                Text("Withdraw")
                    .padding(8)
                
                Spacer()
            }
            .padding()
            .buttonStyle(.borderedProminent)
        }
        .navigationTitle("Withdraw")
        .sheet(isPresented: $accountSheetOpen) {
            BankAccountSheet(
                accounts: viewModel.state.bankAccounts,
                onClick: { account in
                    viewModel.onEvent(
                        event: .OnSelectAccount(bankAccount: account)
                    )
                    
                    accountSheetOpen = false
                }
            )
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .navigationDestination(
            isPresented: Binding(
                get: {
                    viewModel.state.withdrawSuccess
                },
                set: {
                    viewModel.onEvent(event: .UpdateWithdrawResult(result: $0))
                }
            )
        ) {
            VerifyPinScreen(
                onDismiss: {
                    dismiss()
                }
            )
        }
    }
}

struct WithdrawScreen_Previews: PreviewProvider {
    static var previews: some View {
        WithdrawScreen()
    }
}
