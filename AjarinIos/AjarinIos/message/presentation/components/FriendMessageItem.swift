//
//  FriendMessageItem.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FriendMessageItem: View {
    let text: String
    let screenWidth = UIScreen.main.bounds.size.width
    
    var body: some View {
        Text(text)
            .padding()
            .background(
                Color.green
                    .opacity(0.3)
                    .cornerRadius(
                        16,
                        corners: [.bottomLeft, .bottomRight, .topRight]
                    )
            )
            .frame(width: screenWidth * 0.75, alignment: .leading)
    }
}

struct FriendMessageItem_Previews: PreviewProvider {
    static var previews: some View {
        FriendMessageItem(
            text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sodales rhoncus enim in lobortis. Sed ex est, ultricies vel tempor vel, sagittis eu massa. Ut placerat, turpis vel imperdiet aliquet, lacus lectus aliquam odio, ut placerat nibh nibh eu ex. Phasellus gravida elit et mattis posuere. Cras ut ornare turpis. In nec tristique mauris, id rhoncus turpis. Duis neque odio, porta vitae felis eget, pharetra fringilla nisl. Praesent congue lacus erat, eu pharetra nulla volutpat a"
        )
    }
}
