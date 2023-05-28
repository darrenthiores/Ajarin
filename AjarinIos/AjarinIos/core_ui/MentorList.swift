import SwiftUI
import shared

struct MentorList: View {
    let mentors: [Mentor]
    
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
                    ForEach(mentors, id: \.id) { mentor in
                        NavigationLink {
                            Text("\(mentor.name)")
                        } label: {
                            MentorCard(
                                mentor: mentor,
                                height: geo.size.width/3 * 2,
                                width: geo.size.width/2 - 8
                            )
                        }
                        .buttonStyle(.plain)
                    }
                }
            }
        }
    }
}

struct MentorList_Previews: PreviewProvider {
    static var previews: some View {
        MentorList(
            mentors: [
                Mentor(
                    id: "M1",
                    photoUrl: "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg?quality=85&w=1600",
                    name: "Steven",
                    education: "SMA 1",
                    rating: "4.5",
                    courses: [
                        Course(
                            id: "7",
                            name: "Geografi"
                        ),
                        Course(
                            id: "8",
                            name: "Akuntansi"
                        )
                    ],
                    price: "50.000",
                    priceCategory: "50k-100k"
                ),
                Mentor(
                    id: "M2",
                    photoUrl: "https://img.resized.co/breaking-news/eyJkYXRhIjoie1widXJsXCI6XCJodHRwczpcXFwvXFxcL2ltYWdlLmFzc2V0cy5wcmVzc2Fzc29jaWF0aW9uLmlvXFxcL3YyXFxcL2ltYWdlXFxcL3Byb2R1Y3Rpb25cXFwvNWI4MjEzODg4MmI4NjMxYmFiYTYyMGM4MzYyZmYwMzFZMjl1ZEdWdWRITmxZWEpqYUdGd2FTd3hOamMzT0RJeU1ESTFcXFwvMi43MDA3NjY1OC5qcGc_dz02NDBcIixcIndpZHRoXCI6XCI2NDBcIixcImhlaWdodFwiOlwiOTYwXCIsXCJkZWZhdWx0XCI6XCJodHRwczpcXFwvXFxcL3d3dy5icmVha2luZ25ld3MuaWVcXFwvaW1hZ2VzXFxcL25vLWltYWdlLnBuZ1wiLFwib3B0aW9uc1wiOntcIm91dHB1dFwiOlwid2VicFwifX0iLCJoYXNoIjoiNmExMWI0YjZhNmQ1MWE3MmNiYmY2NmNkZTQ1ZjE4YzQxY2M4MzA3NCJ9/will-smith-makes-first-in-person-awards-show-appearance-since-oscars-slap.jpg?w=640",
                    name: "Kevin",
                    education: "SMA 2",
                    rating: "4.1",
                    courses: [
                        Course(
                            id: "2",
                            name: "Biologi"
                        ),
                        Course(
                            id: "3",
                            name: "Fisika"
                        ),
                        Course(
                            id: "4",
                            name: "Kimia"
                        ),
                        Course(
                            id: "5",
                            name: "Matematika"
                        )
                    ],
                    price: "70.000",
                    priceCategory: "50k-100k"
                ),
                Mentor(
                    id: "M3",
                    photoUrl: "https://m0.her.ie/wp-content/uploads/2018/01/07093633/GettyImages-887815620.jpg",
                    name: "Stevan",
                    education: "SMA 1",
                    rating: "4.9",
                    courses: [
                        Course(
                            id: "8",
                            name: "Akuntansi"
                        ),
                        Course(
                            id: "9",
                            name: "Sejarah"
                        ),
                        Course(
                            id: "10",
                            name: "Sosiologi"
                        )
                    ],
                    price: "30.000",
                    priceCategory: "<50k"
                ),
                Mentor(
                    id: "M4",
                    photoUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsy3zU6gHCYIgHQ1hKv1ASWQ62U_Jpe3Wdfg&usqp=CAU",
                    name: "Darren",
                    education: "SMA 3",
                    rating: "4.4",
                    courses: [
                        Course(
                            id: "5",
                            name: "Matematika"
                        ),
                        Course(
                            id: "6",
                            name: "Ekonomi"
                        )
                    ],
                    price: "100.000",
                    priceCategory: "50k-100k"
                )
            ]
        )
    }
}
