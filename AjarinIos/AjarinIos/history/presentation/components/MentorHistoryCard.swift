//
//  MentorHistoryCard.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MentorHistoryCard: View {
    let history: Order
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        HStack {
            AsyncImage(
                url: URL(string: history.mentorImgUrl),
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
                Text(history.mentorName)
                
                Text(history.course.name)
                    .font(.subheadline)
                    .foregroundColor(.gray)
                
                Spacer()
                    .frame(height: 16)
                
                Text("Rp. \(history.totalPrice)")
                    .fontWeight(.semibold)
            }
            
            Spacer()
            
            VStack(alignment: .trailing) {
                let dateText = LocalDateConverter().localDateToString(
                    date: history.date
                )
                
                Text(dateText)
                    .font(.subheadline)
                
                Text(history.schedule.time)
                    .font(.subheadline)
                
                Spacer()
                    .frame(height: 16)
                
                if history.status == "3" {
                    NavigationLink {
                        AddReviewScreen(sessionId: history.id)
                    } label: {
                        Text("Review")
                    }
                    .buttonStyle(.borderedProminent)
                } else {
                    let message = StatusToMessage().getStatusMessage(
                        status: history.status
                    )
                    
                    Text(message)
                        .font(.subheadline)
                        .fontWeight(.semibold)
                }
            }
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

struct MentorHistoryCard_Previews: PreviewProvider {
    static var previews: some View {
        MentorHistoryCard(
            history: .init(
                id: "H1",
                mentorId: "M1",
                mentorName: "Steven",
                mentorImgUrl: "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg?quality=85&w=1600",
                userId: "U1",
                userName: "Darren Thiores",
                userImgUrl: "",
                course: Course(
                    id: "7",
                    name: "Geografi"
                ),
                schedule: Session(
                    id: "2",
                    time: "10:00 - 11:30"
                ),
                date: LocalDateConverter().currentDate(),
                mentorPrice: "50.000",
                totalPrice: "50.000",
                status: "3",
                paymentMethod: PaymentMethod(
                    id: "0",
                    name: "Dana"
                )
            )
        )
    }
}
