//
//  ReviewItem.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ReviewItem: View {
    let review: Review
    let geo: GeometryProxy
    let onImageClick: (Int) -> Void
    
    var body: some View {
        VStack(alignment: .leading) {
            // header
            HStack {
                AsyncImage(
                    url: URL(string: "https://fulaby-comment.oss-ap-southeast-5.aliyuncs.com/c1fbd653-29fe-4df3-8378-0afeb10b0e94-fulabyFeed.jpeg"),
                    content: { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(
                                width: 50,
                                height: 50
                            )
                            .clipShape(Circle())
                    },
                    placeholder: {
                        ZStack {
                            ProgressView()
                                .frame(alignment: .center)
                        }
                        .frame(
                            width: 50,
                            height: 50
                        )
                        .background(.ultraThinMaterial)
                        .clipShape(Circle())
                    }
                )

                Text(review.username)
                
                Spacer()
                
                HStack(alignment: .center, spacing: 2) {
                    Text("(\(review.rating))")
                    Image(systemName: "star.fill")
                        .foregroundColor(.yellow)
                }
            }
            
            Group {
                Spacer()
                    .frame(height: 8)
                
                Divider()
                
                Spacer()
                    .frame(height: 8)
            }
            
            // body
            ReviewImageView(
                imageUrls: review.imagesUrl,
                geo: geo,
                onImageClick: { index in
                    onImageClick(index)
                }
            )
            
            Spacer()
                .frame(height: 8)
            
            ExpandableText(
                text: review.comment
            )
            .font(.subheadline)
            
            Group {
                Spacer()
                    .frame(height: 8)
                
                Divider()
                
                Spacer()
                    .frame(height: 8)
            }
            
            // bottom
            Text("Reviewed at: \(review.reviewDate)")
                .font(.body)
            
            Text("Session: \(review.sessionId)")
                .font(.body)
        }
    }
}

struct ReviewItem_Previews: PreviewProvider {
    static var previews: some View {
        GeometryReader { geo in
            ReviewItem(
                review: .init(
                    reviewId: "R1",
                    userId: "U1",
                    mentorId: "M1",
                    username: "darren thiores",
                    userPhotoUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbmB8QjPQMaiVi3yB0IckvPI1yiaYQLaAQ4g&usqp=CAU",
                    rating: "4.6",
                    comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    imagesUrl: [
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbmB8QjPQMaiVi3yB0IckvPI1yiaYQLaAQ4g&usqp=CAU",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTbmB8QjPQMaiVi3yB0IckvPI1yiaYQLaAQ4g&usqp=CAU"
                    ],
                    reviewDate: "31-05-2023",
                    sessionId: "1"
                ),
                geo: geo,
                onImageClick: { _ in }
            )
        }
    }
}
