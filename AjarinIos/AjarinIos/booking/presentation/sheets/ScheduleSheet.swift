//
//  ScheduleSheet.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ScheduleSheet: View {
    let date: Kotlinx_datetimeLocalDate?
    let converter = LocalDateConverter()
    let onSelect: (Session) -> Void
    
    var body: some View {
        let pickedDate = Date(
            timeIntervalSince1970: TimeInterval(
                converter.localDateToEpoch(
                    date: date ?? converter.currentDate()
                ) / 1000
            )
        )
        let currentDate = Date()
        let calendar = Calendar.current
        let hour = calendar.component(.hour, from: currentDate)
        let currentSession = Session.companion.currentSession(
            hour: Int32(hour)
        )
        let isToday = calendar.isDateInToday(pickedDate)
        
        List {
            Text("Select Session")
                .padding(.vertical)
                .listRowSeparator(.hidden)
            
            ForEach(sessions, id: \.id) { session in
                let disabled = isToday && ((Int(session.id) ?? 0) <= (Int(currentSession) ?? 0))
                
                Button {
                    onSelect(session)
                } label: {
                    HStack {
                        Text("Session \(session.id)")
                        
                        Spacer()
                        
                        Text(":")
                        
                        Spacer()
                        
                        Text(session.time)
                    }
                    .padding(8)
                    .foregroundColor(
                        disabled ? .gray : .black
                    )
                }
                .buttonStyle(.plain)
            }
        }
        .listStyle(.plain)
    }
}

struct ScheduleSheet_Previews: PreviewProvider {
    static var previews: some View {
        ScheduleSheet(
            date: nil,
            onSelect: { _ in }
        )
    }
}
