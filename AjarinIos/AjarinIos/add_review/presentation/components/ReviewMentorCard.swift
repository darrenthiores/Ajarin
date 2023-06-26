//
//  ReviewMentorCard.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ReviewMentorCard: View {
    let mentorPhotoUrl: String?
    let mentorName: String?
    let mentorPrice: String?
    let courseName: String?
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        HStack {
            AsyncImage(
                url: URL(string: mentorPhotoUrl ?? ""),
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
            
            Spacer()
                .frame(width: 16)
            
            VStack(alignment: .leading) {
                Text(mentorName ?? "Loading...")
                
                Text(courseName ?? "Loading...")
                    .font(.subheadline)
                    .foregroundColor(.gray)
                
                Spacer()
                    .frame(height: 16)
                
                Text("Rp. \(mentorPrice ?? "0")")
                    .fontWeight(.semibold)
            }
            
            Spacer()
        }
        .padding()
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

struct ReviewMentorCard_Previews: PreviewProvider {
    static var previews: some View {
        ReviewMentorCard(
            mentorPhotoUrl: nil,
            mentorName: nil,
            mentorPrice: nil,
            courseName: nil
        )
    }
}
