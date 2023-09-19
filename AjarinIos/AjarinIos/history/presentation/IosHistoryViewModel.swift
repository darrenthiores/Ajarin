//
//  IosHistoryViewModel.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension HistoryScreen {
    @MainActor class IosHistoryViewModel: ObservableObject {
        private let viewModel: HistoryViewModel
        
        @Published var state: HistoryState = HistoryState(
            //historySessions: [],
            historyLoading: false,
            historyError: nil
        )
        
        private var handle: DisposableHandle?
        
        init() {
            self.viewModel = HistoryViewModel(
                coroutineScope: nil
            )
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
