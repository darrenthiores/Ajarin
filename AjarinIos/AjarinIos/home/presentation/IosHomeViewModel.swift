import Foundation
import shared

extension HomeScreen {
    @MainActor class IosHomeViewModel: ObservableObject {
        private let viewModel: HomeViewModel
        
        @Published var state: HomeState = HomeState(
            courses: [],
            selectedCourse: Course(
                id: "0",
                name: "All"
            ),
            //mentors: [],
            unreadMessageCount: 0
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getMentors: GetMentors
            @Inject var searchMentor: SearchMentor
            @Inject var getUnreadCount: GetUnreadCount
            
            self.viewModel = HomeViewModel(
                getMentors: getMentors,
                searchMentor: searchMentor,
                getUnreadCount: getUnreadCount,
                coroutineScope: nil
            )
            
            self.viewModel.doInitUnreadCount(userId: "U1")
        }
        
        func onEvent(event: HomeEvent) {
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

