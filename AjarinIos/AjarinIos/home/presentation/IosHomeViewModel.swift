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
            mentors: []
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var searchMentorByCourse: SearchMentorByCourse
            
            self.viewModel = HomeViewModel(
                searchMentorByCourse: searchMentorByCourse,
                coroutineScope: nil
            )
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

