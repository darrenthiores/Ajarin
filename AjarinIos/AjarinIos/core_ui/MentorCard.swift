import SwiftUI
import shared

struct MentorCard: View {
    let mentor: Mentor
    let height: CGFloat
    let width: CGFloat
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        VStack(alignment: .leading) {
            AsyncImage(
                url: URL(string: mentor.photoUrl),
                content: { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(
                            width: width,
                            height: height
                        )
                },
                placeholder: {
                    ZStack {
                        ProgressView()
                            .frame(alignment: .center)
                    }
                    .background(.ultraThinMaterial)
                    .frame(
                        width: width,
                        height: height
                    )
                }
            )
            .cornerRadius(4)
            
            Spacer()
                .frame(height: 8)
            
            VStack(alignment: .leading) {
                Text(mentor.name)
                    .font(.title2)
                    .frame(maxWidth: .infinity, alignment: .leading)
                
                Text(mentor.education)
                    .font(.body)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .foregroundColor(.gray)
                
                Spacer()
                    .frame(height: 12)
                
                WrappingHStack(
                    horizontalSpacing: 4,
                    verticalSpacing: 4
                ) {
                    InformationItem(
                        iconName: "hand.thumbsup.fill",
                        text: mentor.rating
                    )
                    
                    ForEach(mentor.courses, id: \.id) { course in
                        InformationItem(
                            iconName: "book.fill",
                            text: course.name
                        )
                    }
                }
                
                Spacer()
                    .frame(height: 16)
                
                Text("Rp. \(mentor.price)")
                    .font(.title2)
            }
            .padding()
        }
        .frame(width: width)
        .background(
            Rectangle()
                .fill(
                    colorScheme == .dark ? Color(UIColor.secondarySystemFill) : Color(UIColor.systemBackground)
                )
                .cornerRadius(4)
                .shadow(radius: 2)
        )

    }
}

struct MentorCard_Previews: PreviewProvider {
    static var previews: some View {
        MentorCard(
            mentor: Mentor(
                id: "0",
                photoUrl: "https://fulaby-feed-thumbnail.oss-ap-southeast-5.aliyuncs.com/05aa10b1-7786-4c6f-8cb1-1db90a74539d-fulabyFeedThumbnail.jpeg",
                name: "Jonni",
                education: "SMA 2",
                rating: "4.5",
                courses: [
                    Course(
                        id: "0",
                        name: "Matematika"
                    )
                ],
                price: "15.000",
                priceCategory: "<50k"
            ),
            height: 180,
            width: 180
        )
    }
}
