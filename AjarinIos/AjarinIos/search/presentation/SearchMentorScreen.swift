import SwiftUI

struct SearchMentorScreen: View {
    @StateObject private var viewModel = IosSearchMentorViewModel()
    @State private var showFilter: Bool = false
    
    var body: some View {
        NavigationStack {
            VStack {
                SearchMentorHeader(
                    searchText: Binding(
                        get: {
                            viewModel.state.searchText
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnSearchChange(
                                    newText: $0
                                )
                            )
                        }
                    ),
                    filtered: !viewModel.iosState.mentors.isEmpty,
                    onSearch: {
                        viewModel.iosOnEvent(
                            event: .OnSearch
                        )
                    },
                    onFilter: {
                        showFilter.toggle()
                    },
                    onClearFilter: {
                        viewModel.onEvent(
                            event: .OnReset()
                        )
                        
                        viewModel.onEvent(
                            event: .OnApply()
                        )
                        
                        viewModel.iosOnEvent(event: .Clear)
                    }
                )
                .padding()
                
                if viewModel.iosState.mentors.isEmpty {
                    SearchMentorDefault(
                        courses: viewModel.state.courses,
                        onEvent: { event in
                            viewModel.onEvent(
                                event: event
                            )
                        },
                        iosOnEvent: { event in
                            viewModel.iosOnEvent(event: event)
                        }
                    )
                } else {
                    MentorList(
                        mentors: viewModel.iosState.mentors,
                        onAppear: {
                            if !viewModel.iosState.endMentorReached
                                && !viewModel.iosState.isFetchingMentors {
                                viewModel.iosOnEvent(event: .FetchMentor
                                )
                            }
                        }
                    )
                    .padding(.horizontal)
                }
            }
            .navigationTitle("Search Mentor")
            .sheet(isPresented: $showFilter) {
                MentorFilterSheet(
                    state: viewModel.state,
                    onEvent: { event in
                        viewModel.onEvent(event: event)
                        
                        if event == .OnApply() {
                            viewModel.iosOnEvent(event: .OnSearch)
                            showFilter = false
                            
                            return
                        }
                    }
                )
            }
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
        }
    }
}

struct SearchMentorScreen_Previews: PreviewProvider {
    static var previews: some View {
        SearchMentorScreen()
    }
}
