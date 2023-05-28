import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject private var viewModel = IosHomeViewModel()
    
    var body: some View {
        NavigationView {
            VStack {
                HomeHeader(
                    courses: viewModel.state.courses,
                    currentCourse: viewModel.state.selectedCourse,
                    onClick: { course in
                        viewModel.onEvent(
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
                            viewModel.onEvent(
                                event: .OnSelectCourse(course: $0)
                            )
                        }
                    )
                ) {
                    MentorList(
                        mentors: viewModel.state.mentors
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
                            mentors: viewModel.state.mentors
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
