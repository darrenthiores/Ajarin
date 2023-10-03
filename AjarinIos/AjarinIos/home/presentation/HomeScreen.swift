import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject private var viewModel = IosHomeViewModel()
    
    var body: some View {
        NavigationStack {
            VStack {
                HomeHeader(
                    courses: viewModel.state.courses,
                    currentCourse: viewModel.state.selectedCourse,
                    unreadMessageCount: Int(viewModel.state.unreadMessageCount),
                    onClick: { course in
                        viewModel.iosOnEvent(
                            event: .OnSelectCourse(course: course)
                        )
                    }
                )
                
                Spacer()
                    .frame(height: 8)
                
                TabView(
                    selection: Binding(
                        get: {
                            viewModel.state.selectedCourse
                        },
                        set: {
                            viewModel.iosOnEvent(
                                event: .OnSelectCourse(course: $0)
                            )
                        }
                    )
                ) {
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
                    .tag(
                        Course(
                            id: "0",
                            name: "All"
                        )
                    )
                    .gesture(DragGesture())
                    
                    ForEach(viewModel.state.courses, id: \.id) { course in
                        MentorList(
                            mentors: viewModel.iosState.searchMentors,
                            onAppear: {
                                if !viewModel.iosState.endSearchMentorReached
                                    && !viewModel.iosState.isFetchingSearchMentors {
                                    viewModel.iosOnEvent(event: .FetchSearchMentor
                                    )
                                }
                            }
                        )
                        .padding(.horizontal)
                        .tag(
                            course
                        )
                        .gesture(DragGesture())
                    }
                }
                .tabViewStyle(
                    .page(indexDisplayMode: .never)
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

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
