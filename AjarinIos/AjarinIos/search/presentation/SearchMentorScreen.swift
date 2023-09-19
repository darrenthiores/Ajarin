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
                    filtered: false,//!viewModel.state.mentors.isEmpty,
                    onSearch: {
//                        viewModel.onEvent(
//                            event: .OnSearch()
//                        )
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
                    }
                )
                .padding()
                
                ZStack {
                    SearchMentorDefault(
                        courses: viewModel.state.courses,
                        onEvent: { event in
                            viewModel.onEvent(
                                event: event
                            )
                        }
                    )
//                    if viewModel.state.mentors.isEmpty {
//                        SearchMentorDefault(
//                            courses: viewModel.state.courses,
//                            onEvent: { event in
//                                viewModel.onEvent(
//                                    event: event
//                                )
//                            }
//                        )
//                    } else {
//                        MentorList(
//                            mentors: viewModel.state.mentors
//                        )
//                        .padding(.horizontal)
//                    }
                }
            }
            .navigationTitle("Search Mentor")
            .sheet(isPresented: $showFilter) {
                MentorFilterSheet(
                    state: viewModel.state,
                    onEvent: { event in
                        if event == .OnApply() {
                            showFilter = false
                        }
                        
                        viewModel.onEvent(event: event)
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
