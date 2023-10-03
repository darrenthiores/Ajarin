import Foundation
import shared

extension SearchMentorScreen {
    @MainActor class IosSearchMentorViewModel: ObservableObject {
        private let viewModel: SearchMentorViewModel
        @Inject var searchMentor: SearchMentor
        
        @Published var state: SearchMentorState = SearchMentorState(
            searchText: "",
            //mentors: [],
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
        @Published var iosState: IosSearchMentorState = IosSearchMentorState()
        
        private var handle: DisposableHandle?
        private var fetchingSearchMentorJob: Task<(), Never>? = nil
        
        init() {
            self.viewModel = SearchMentorViewModel(
                coroutineScope: nil
            )
        }
        
        func onEvent(event: SearchMentorEvent) {
            viewModel.onEvent(event: event)
        }
        
        func iosOnEvent(event: IosSearchMentorEvent) {
            switch event {
            case .FetchMentor:
                fetchMentors()
            case .SelectCourse(course: let course):
                onEvent(event: .OnPickCourse(course: course))
                onEvent(event: .OnApply())
                searchMentors(course: course)
            case .OnSearch:
                searchMentors()
            case .Clear:
                iosState.endMentorReached = false
                iosState.mentorError = nil
                iosState.currentMentorPage = 1
                iosState.isFetchingMentors = false
                iosState.mentors = []
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
        
        func searchMentors(
            course: Course
        ) {
            fetchingSearchMentorJob?.cancel()
            fetchingSearchMentorJob = Task {
                iosState.endMentorReached = false
                iosState.mentorError = nil
                iosState.currentMentorPage = 1
                iosState.isFetchingMentors = true
                
                do {
                    let result = try await searchMentor.invoke(
                        name: state.searchText,
                        course: course,
                        rating: state.rating,
                        price: state.price,
                        education: state.education,
                        page: Int32(iosState.currentMentorPage)
                    )
                    
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
        
        func searchMentors() {
            if state.searchText.isEmpty
                && state.tempCourse == nil
                && state.tempEducation.isEmpty
                && state.tempPrice.isEmpty
                && state.tempRating == 0
            {
                iosState.mentors = []

                return
            }
            
            fetchingSearchMentorJob?.cancel()
            fetchingSearchMentorJob = Task {
                iosState.endMentorReached = false
                iosState.mentorError = nil
                iosState.currentMentorPage = 1
                iosState.isFetchingMentors = true
                
                do {
                    let result = try await searchMentor.invoke(
                        name: state.searchText,
                        course: state.selectedCourse,
                        rating: state.rating,
                        price: state.price,
                        education: state.education,
                        page: Int32(iosState.currentMentorPage)
                    )
                    
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
        
        func fetchMentors() {
            if state.searchText.isEmpty
                && state.selectedCourse == nil
                && state.education.isEmpty
                && state.price.isEmpty
                && state.rating == 0
            {
                iosState.mentors = []

                return
            }
            
            fetchingSearchMentorJob?.cancel()
            fetchingSearchMentorJob = Task {
                iosState.isFetchingMentors = true
                
                do {
                    let result = try await searchMentor.invoke(
                        name: state.searchText,
                        course: state.selectedCourse,
                        rating: state.rating,
                        price: state.price,
                        education: state.education,
                        page: Int32(iosState.currentMentorPage)
                    )
                    
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
    }
}


