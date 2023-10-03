import SwiftUI
import shared

struct MentorFilterSheet: View {
    let state: SearchMentorState
    let onEvent: (SearchMentorEvent) -> Void
    
    var body: some View {
        VStack {
            HStack {
                Text("Filter")
                    .font(.title2)
                
                Spacer()
                
                Button {
                    onEvent(
                        .OnReset()
                    )
                } label: {
                    Text("Reset")
                        .font(.caption)
                        .foregroundColor(.red)
                }
            }
            .padding()
            
            List {
                Section("Rating") {
                    Button {
                        onEvent(
                            .OnToggleRating()
                        )
                    } label: {
                        FilterItem(
                            text: "⭐ 4 keatas",
                            isSelected: state.tempRating != 0
                        )
                    }
                }
                .buttonStyle(.plain)
                .listRowSeparator(.hidden)
                
                Section("Course") {
                    WrappingHStack(
                        horizontalSpacing: 10,
                        verticalSpacing: 10
                    ) {
                        ForEach(state.courses, id: \.id) { item in
                            Button {
                                onEvent(
                                    .OnPickCourse(course: item)
                                )
                            } label: {
                                FilterItem(
                                    text: item.name,
                                    isSelected: state.tempCourse?.id == item.id
                                )
                            }
                        }
                    }
                }
                .buttonStyle(.plain)
                .listRowSeparator(.hidden)
                
                Section("Education") {
                    WrappingHStack(
                        horizontalSpacing: 10,
                        verticalSpacing: 10
                    ) {
                        ForEach(state.educations, id: \.self) { item in
                            Button {
                                onEvent(
                                    .OnPickEducation(education: item)
                                )
                            } label: {
                                FilterItem(
                                    text: item,
                                    isSelected: state.tempEducation == item
                                )
                            }
                        }
                    }
                }
                .buttonStyle(.plain)
                .listRowSeparator(.hidden)
                
                Section("Price") {
                    WrappingHStack(
                        horizontalSpacing: 10,
                        verticalSpacing: 10
                    ) {
                        ForEach(state.prices, id: \.self) { item in
                            Button {
                                onEvent(
                                    .OnPickPrice(price: item)
                                )
                            } label: {
                                FilterItem(
                                    text: item,
                                    isSelected: state.tempPrice == item
                                )
                            }
                        }
                    }
                }
                .buttonStyle(.plain)
                .listRowSeparator(.hidden)
                
                Spacer()
                    .frame(height: 50)
                    .listRowSeparator(.hidden)
            }
            .listStyle(.plain)
            .overlay {
                VStack {
                    Spacer()
                    
                    if state.tempCourse != state.selectedCourse ||
                        state.tempRating != state.rating ||
                        state.tempPrice != state.price ||
                        state.tempEducation != state.education
                    {
                        Button {
                            onEvent(
                                .OnApply()
                            )
                        } label: {
                            Spacer()
                            
                            Text("Apply")
                            
                            Spacer()
                        }
                        .buttonStyle(.borderedProminent)
                    }
                }
                .padding()
            }
        }
    }
}

struct MentorFilterSheet_Previews: PreviewProvider {
    static var previews: some View {
        MentorFilterSheet(
            state: SearchMentorState(
                searchText: "",
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
                tempCourse: nil,
                selectedCourse: nil,
                tempRating: 0,
                rating: 0,
                prices: [
                    "<50k",
                    "50k-100k",
                    "100k-200k",
                    ">200k"
                ],
                tempPrice: "",
                price: "",
                educations: [
                    "SMA 1",
                    "SMA 2",
                    "SMA 3",
                    "S1",
                    "S2"
                ],
                tempEducation: "",
                education: ""
            ),
            onEvent: { event in }
        )
    }
}

