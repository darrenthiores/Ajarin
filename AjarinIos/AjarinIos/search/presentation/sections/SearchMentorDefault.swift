import SwiftUI
import shared

struct SearchMentorDefault: View {
    let courses: [Course]
    let onEvent: (SearchMentorEvent) -> Void
    
    var body: some View {
        GeometryReader { geo in
            ScrollView {
                LazyVGrid(
                    columns: [
                        GridItem(.flexible()),
                        GridItem(.flexible())
                    ],
                    spacing: 8
                ) {
                    ForEach(courses, id: \.id) { course in
                        Button {
                            onEvent(
                                .OnPickCourse(
                                    course: course
                                )
                            )
                            
                            onEvent(
                                .OnApply()
                            )
                            
                            onEvent(
                                .OnSearch()
                            )
                        } label: {
                            ZStack(alignment: .topLeading) {
                                Color.clear
                                
                                Text(course.name)
                                    .padding()
                                    .font(.title3)
                                    .fontWeight(.bold)
                                    .foregroundColor(.white)
                            }
                            .frame(
                                width: geo.size.width/2 - 16,
                                height: geo.size.width/4
                            )
                            .background(
                                RoundedRectangle(
                                    cornerSize: CGSize(
                                        width: 8,
                                        height: 8
                                    )
                                )
                                .frame(
                                    width: geo.size.width/2 - 16,
                                    height: geo.size.width/4
                                )
                                .foregroundColor(
                                    colors[(Int(course.id) ?? 1) - 1]
                                )
                            )
                        }
                        .listRowInsets(EdgeInsets())
                    }
                }
                .padding(.horizontal)
                .listRowSeparator(.hidden)
            }
            .listStyle(.plain)
        }
    }
}

struct SearchMentorDefault_Previews: PreviewProvider {
    static var previews: some View {
        SearchMentorDefault(
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
            onEvent: { event in }
        )
    }
}

private let colors: [Color] = [
     .green,
     .cyan,
     .blue,
     .indigo,
     .mint,
     .pink,
     .orange,
     .yellow,
     .teal,
     .red
]
