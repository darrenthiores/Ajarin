//
//  MentorSchedule.swift
//  AjarinIos
//
//  Created by Darren Thiores on 22/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

let sessions = [
    Session(
        id: "1",
        time: "08:00 - 09:30"
    ),
    Session(
        id: "2",
        time: "10:00 - 11:30"
    ),
    Session(
        id: "3",
        time: "12:00 - 13:30"
    ),
    Session(
        id: "4",
        time: "14:00 - 15:30"
    ),
    Session(
        id: "5",
        time: "16:00 - 17:30"
    ),
    Session(
        id: "6",
        time: "18:00 - 19:30"
    ),
    Session(
        id: "7",
        time: "20:00 - 21:30"
    )
]

struct MentorSchedule: View {
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        let date = Date()
        let calendar = Calendar.current
        let hour = calendar.component(.hour, from: date)
        let currentSession = Session.companion.currentSession(
            hour: Int32(hour)
        )
        
        LazyVStack {
            ForEach(sessions, id: \.id) { session in
                HStack {
                    Text("Session \(session.id)")
                    
                    Spacer()
                    
                    Text(":")
                    
                    Spacer()
                    
                    Text(session.time)
                }
                .padding(8)
                .foregroundColor(
                    (session.id == currentSession) ? .purple : .black
                )
                
                Divider()
            }
        }
        .padding(.top)
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

struct MentorSchedule_Previews: PreviewProvider {
    static var previews: some View {
        MentorSchedule()
    }
}
