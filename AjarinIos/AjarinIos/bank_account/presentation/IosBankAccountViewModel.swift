//
//  IosBankAccountViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension BankAccountScreen {
    @MainActor class IosBankAccountViewModel: ObservableObject {
        private let viewModel: BankAccountViewModel
        
        @Published var state: BankAccountState = BankAccountState(
            bankAccounts: [],
            bankAccountsLoading: false,
            bankAccountsError: nil
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = BankAccountViewModel(
                coroutineScope: nil
            )
        }
        
        func initAccount() {
            viewModel.doInit()
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
