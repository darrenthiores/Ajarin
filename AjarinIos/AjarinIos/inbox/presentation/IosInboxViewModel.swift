import Foundation
import shared

extension InboxScreen {
    @MainActor class IosInboxViewModel: ObservableObject {
        private let viewModel: InboxViewModel
        
        @Published var state: InboxState = InboxState(
            inbox: [],
            isLoading: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getInbox: GetInbox
            @Inject var getMentorById: GetMentorById
            
            self.viewModel = InboxViewModel(
                getInbox: getInbox,
                getMentorById: getMentorById,
                coroutineScope: nil
            )
        }
        
        func initInbox(
            userId: String
        ) {
            viewModel.doInitInbox(userId: userId)
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
