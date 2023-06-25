import SwiftUI
import shared

struct HomeHeader: View {
    let courses: [Course]
    let currentCourse: Course
    let unreadMessageCount: Int
    let onClick: (Course) -> Void
    
    var body: some View {
        VStack {
            HStack {
                Text("Ajarin")
                    .foregroundColor(.primary)
                    .font(.title2)
                
                Spacer()
                
                NavigationLink {
                    InboxScreen(userId: "U1")
                } label: {
                    ZStack {
                        Image(systemName:"ellipsis.message")
                        
                        if unreadMessageCount > 0 {
                            Color
                                .red
                                .frame(
                                    width: 15,
                                    height: 15
                                )
                                .clipShape(Circle())
                                .overlay {
                                    Text("\(unreadMessageCount)")
                                        .font(.caption2)
                                        .foregroundColor(
                                            Color(UIColor.systemBackground)
                                        )
                                }
                                .offset(x: 8, y: -8)
                        }
                    }
                }
            }
            
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
            }
            .font(.headline)
        }
        .padding(.horizontal)
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
            unreadMessageCount: 1,
            onClick: { _ in }
        )
    }
}
