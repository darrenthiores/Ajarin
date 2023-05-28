import SwiftUI
import shared

struct HomeHeader: View {
    let courses: [Course]
    let currentCourse: Course
    let onClick: (Course) -> Void
    
    var body: some View {
        ScrollView(
            .horizontal,
            showsIndicators: false
        ) {
            HStack {
                HomeTabButton(
                    title: "All",
                    onClick: {
                        onClick(
                            Course(
                                id: "0",
                                name: "All"
                            )
                        )
                    },
                    isSelected: currentCourse.id == "0"
                )
                .padding(.horizontal, 3)
                
                ForEach(courses, id: \.id) { course in
                    HomeTabButton(
                        title: "#\(course.name)",
                        onClick: {
                             onClick(course)
                        },
                        isSelected: currentCourse.id == course.id
                    )
                    .padding(.horizontal, 3)
                }
            }
            .padding(.horizontal)
        }
        .font(.headline)
    }
}

struct HomeHeader_Previews: PreviewProvider {
    static var previews: some View {
        HomeHeader(
            courses: [
                Course(
                    id: "1",
                    name: "Math"
                ),
                Course(
                    id: "2",
                    name: "Bio"
                )
            ],
            currentCourse: Course(
                id: "0",
                name: "All"
            ),
            onClick: { _ in }
        )
    }
}
