import Foundation
import shared

extension HomeScreen {
    @MainActor class IosHomeViewModel: ObservableObject {
        private let viewModel: HomeViewModel
        @Inject var getMentors: GetMentors
        @Inject var searchMentor: SearchMentor
        
        @Published var state: HomeState = HomeState(
            courses: [],
            selectedCourse: Course(
                id: "0",
                name: "All"
            ),
            unreadMessageCount: 0
        )
        
        @Published var iosState: IosHomeState = IosHomeState()
        
        private var handle: DisposableHandle?
        private var fetchingSearchMentorJob: Task<(), Never>? = nil
        
        init() {
            @Inject var getUnreadCount: GetUnreadCount
            
            self.viewModel = HomeViewModel(
                getUnreadCount: getUnreadCount,
                coroutineScope: nil
            )
            
            self.viewModel.doInitUnreadCount(userId: "U1")
            
            fetchMentors()
        }
        
        func onEvent(event: HomeEvent) {
            viewModel.onEvent(event: event)
        }
        
        func iosOnEvent(event: IosHomeEvent) {
            switch event {
            case .FetchMentor:
                fetchMentors()
            case .FetchSearchMentor:
                fetchSearchMentors()
            case .OnSelectCourse(course: let course):
                onEvent(event: .OnSelectCourse(course: course))
                fetchSearchMentors()
            }
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
        
        func fetchMentors() {
            Task {
                iosState.isFetchingMentors = true
                
                do {
                    let result = try await getMentors.invoke(page: Int32(iosState.currentMentorPage))
                    
                    iosState.isFetchingMentors = false
                    
                    if result.isEmpty {
                        iosState.endMentorReached = true
                    } else {
                        iosState.mentors.append(contentsOf: result)
                        iosState.currentMentorPage += 1
                    }
                    
                    print("fetch mentors success: \(iosState.currentMentorPage-1)")
                } catch let error {
                    iosState.endMentorReached = true
                    iosState.isFetchingMentors = false
                    iosState.mentorError = error
                    
                    print("fetch mentors failed: \(error): \(iosState.currentMentorPage)")
                }
            }
        }
        
        func fetchSearchMentors() {
            fetchingSearchMentorJob?.cancel()
            fetchingSearchMentorJob = Task {
                iosState.isFetchingSearchMentors = true
                
                do {
                    let result = try await searchMentor.invoke(
                        name: "",
                        course: state.selectedCourse,
                        rating: 0,
                        price: "",
                        education: "",
                        page: Int32(iosState.currentSearchMentorPage)
                    )
                    
                    iosState.isFetchingSearchMentors = false
                    
                    if result.isEmpty {
                        iosState.endSearchMentorReached = true
                    } else {
                        iosState.searchMentors.append(contentsOf: result)
                        iosState.currentSearchMentorPage += 1
                    }
                    
                    print("fetch mentors success: \(iosState.currentSearchMentorPage-1)")
                } catch let error {
                    iosState.endSearchMentorReached = true
                    iosState.isFetchingSearchMentors = false
                    iosState.searchMentorError = error
                    
                    print("fetch mentors failed: \(error): \(iosState.currentSearchMentorPage)")
                }
            }
        }
    }
}

