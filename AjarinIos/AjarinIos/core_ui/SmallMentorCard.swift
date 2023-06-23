//
//  SmallMentorCard.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SmallMentorCard: View {
    let mentor: Mentor?
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        HStack {
            AsyncImage(
                url: URL(string: mentor?.photoUrl ?? ""),
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
                Text(mentor?.name ?? "Loading...")
                
                Text(mentor?.education ?? "Loading...")
                    .font(.subheadline)
                    .foregroundColor(.gray)
            }
            
            Spacer()
            
            Text("Rp. \(mentor?.price ?? "0")")
                .fontWeight(.semibold)
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

struct SmallMentorCard_Previews: PreviewProvider {
    static var previews: some View {
        SmallMentorCard(mentor: nil)
    }
}
