//
//  IosWithdrawViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension WithdrawScreen {
    @MainActor class IosWithdrawViewModel: ObservableObject {
        private let viewModel: WithdrawViewModel
        
        @Published var state: WithdrawState = WithdrawState(
            bankAccounts: [],
            walletBalance: "",
            bankAccountsLoading: false,
            bankAccountsError: nil,
            selectedAccount: nil,
            selectedAccountError: nil,
            amount: "",
            amountError: nil,
            note: "",
            noteError: nil,
            isWithdrawing: false,
            withdrawSuccess: false,
            withdrawError: nil
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = WithdrawViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: WithdrawEvent) {
            viewModel.onEvent(event: event)
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
