//
//  SmallUserCard.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SmallUserCard: View {
    let user: User?
    let course: Course?
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                ZStack {
                    Color.purple
                    
                    Text(String(user?.name.first ?? "?"))
                        .frame(
                            alignment: .center
                        )
                }
                .frame(
                    width: 50,
                    height: 50
                )
                .clipShape(Circle())
                
                Spacer()
                    .frame(width: 16)
                
                VStack(alignment: .leading) {
                    Text(user?.name ?? "Loading...")
                    
                    Text(user?.email ?? "Loading...")
                        .font(.subheadline)
                        .foregroundColor(.gray)
                }
                
                Spacer()
            }
            
            Spacer()
                .frame(height: 16)
            
            Text("Course : \(course?.name ?? "Loading...")")
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

struct SmallUserCard_Previews: PreviewProvider {
    static var previews: some View {
        SmallUserCard(
            user: nil,
            course: nil
        )
    }
}
