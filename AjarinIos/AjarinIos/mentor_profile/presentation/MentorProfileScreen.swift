import SwiftUI
import shared

struct MentorProfileScreen: View {
    let id: String
    
    @StateObject private var viewModel = IosMentorProfileViewModel()
    @State private var selectedTab: MentorProfileTab = .about
    let screenWidth = UIScreen.main.bounds.size.width
    let screenHeight = UIScreen.main.bounds.size.height
    
    var body: some View {
        GeometryReader { geo in
            ZStack {
                if viewModel.state.isFetching {
                    Text("Loading")
                }
                
                ScrollView {
                    AsyncImage(
                        url: URL(
                            string: viewModel.state.mentor?.photoUrl ?? ""
                        ),
                        content: { image in
                            image
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(
                                    width: screenWidth
                                )
                        },
                        placeholder: {
                            ZStack {
                                ProgressView()
                                    .frame(alignment: .center)
                            }
                            .background(.ultraThinMaterial)
                            .frame(
                                width: screenWidth,
                                height: screenWidth
                            )
                        }
                    )
                    .cornerRadius(4)
                    
                    HStack {
                        TabButton(
                            title: "About",
                            onClick: {
                                selectedTab = .about
                            },
                            isSelected: selectedTab == .about
                        )
                        
                        TabButton(
                            title: "Schedule",
                            onClick: {
                                selectedTab = .schedule
                            },
                            isSelected: selectedTab == .schedule
                        )
                        
                        TabButton(
                            title: "Review",
                            onClick: {
                                selectedTab = .review
                            },
                            isSelected: selectedTab == .review
                        )
                    }
                    .padding(.top)
                    .font(.headline)
                    
                    Divider()
                    
                    if selectedTab == .about {
                        MentorAbout(mentor: viewModel.state.mentor)
                            .padding()
                    }
                    
                    if selectedTab == .schedule {
                        MentorSchedule()
                            .padding()
                    }
                    
                    if selectedTab == .review {
                        MentorReview(
                            reviews: viewModel.state.reviews,
                            geo: geo
                        )
                        .padding()
                    }
                }
                
                VStack {
                    Spacer()
                    
                    HStack {
                        Spacer()
                        
                        Button{
                            
                        } label: {
                            Text("Book")
                                .font(.title2)
                                .foregroundColor(Color.white)
                                .padding()
                        }
                        .background(.primary)
                        .cornerRadius(16)
                        .padding()
                        .shadow(
                            color: Color.black.opacity(0.3),
                            radius: 3,
                            x: 3,
                            y: 3
                        )
                    }
                }
            }
            .navigationTitle(viewModel.state.mentor?.name ?? "Mentor Profile")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItemGroup(placement: .navigationBarTrailing) {
                    NavigationLink {
                        Text("Message")
                    } label: {
                        Image(systemName: "ellipsis.message")
                    }
                    .buttonStyle(.plain)
                    
                    Button {
                        
                    } label: {
                        Image(systemName: "arrowshape.turn.up.right.fill")
                    }
                    .buttonStyle(.plain)
                }
            }
            .onAppear {
                viewModel.startObserving()
                viewModel.initMentor(id: id)
            }
            .onDisappear {
                viewModel.dispose()
            }
        }
    }
}

struct MentorProfileScreen_Previews: PreviewProvider {
    static var previews: some View {
        MentorProfileScreen(id: "M1")
    }
}
