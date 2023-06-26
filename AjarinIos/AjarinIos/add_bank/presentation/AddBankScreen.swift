//
//  AddBankScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AddBankScreen: View {
    @StateObject private var viewModel = IosAddBankViewModel()
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        VStack {
            List {
                Section("Bank Selection") {
                    BankDropDown(
                        bank: viewModel.state.selectedBank,
                        isOpen: viewModel.state.isBankDropDownOpen,
                        selectBank: { bank in
                            viewModel.onEvent(
                                event: .SelectBank(bank: bank)
                            )
                        }
                    )
                }
                .listRowSeparator(.hidden)
                
                Section("Bank Account Number") {
                    DefaultTextField(
                        title: "Account Number",
                        text: Binding(
                            get: {
                                viewModel.state.accountNumber
                            },
                            set: {
                                viewModel.onEvent(
                                    event: .OnAccountNumberChange(number: $0)
                                )
                            }
                        )
                    )
                    .keyboardType(.decimalPad)
                }
                .listRowSeparator(.hidden)
            }
            .listStyle(.plain)
            
            Spacer()
            
            Button {
                viewModel.onEvent(
                    event: .AddBank()
                )
            } label: {
                Spacer()
                
                Text("Add")
                    .padding(8)
                
                Spacer()
            }
            .padding()
            .buttonStyle(.borderedProminent)
        }
        .navigationTitle("Add Bank")
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
        .onChange(of: viewModel.state.saveSuccess) { result in
            if result {
                dismiss()
            }
        }
    }
}

struct AddBankScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddBankScreen()
    }
}
