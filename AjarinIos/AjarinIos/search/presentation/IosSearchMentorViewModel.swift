import Foundation
import shared

extension SearchMentorScreen {
    @MainActor class IosSearchMentorViewModel: ObservableObject {
        private let viewModel: SearchMentorViewModel
        
        @Published var state: SearchMentorState = SearchMentorState(
            searchText: "",
            mentors: [],
            courses: [],
            tempCourse: nil,
            selectedCourse: nil,
            tempRating: 0,
            rating: 0,
            prices: [],
            tempPrice: "",
            price: "",
            educations: [],
            tempEducation: "",
            education: ""
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var searchMentor: SearchMentor
            
            self.viewModel = SearchMentorViewModel(
                searchMentor: searchMentor,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: SearchMentorEvent) {
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


