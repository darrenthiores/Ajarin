//
//  MentorReview.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MentorReview: View {
    let reviews: [Review]
    let geo: GeometryProxy
    
    var body: some View {
        LazyVStack {
            ForEach(reviews, id: \.reviewId) { review in
                ReviewItem(
                    review: review,
                    geo: geo,
                    onImageClick: { index in }
                )
            }
        }
    }
}

struct MentorReview_Previews: PreviewProvider {
    static var previews: some View {
        GeometryReader { geo in
            MentorReview(
                reviews: [],
                geo: geo
            )
        }
    }
}
