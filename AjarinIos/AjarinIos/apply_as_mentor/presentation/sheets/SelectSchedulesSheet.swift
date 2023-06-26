//
//  SelectSchedulesSheet.swift
//  AjarinIos
//
//  Created by Darren Thiores on 26/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SelectSchedulesSheet: View {
    let selectedSchedules: [Session]
    let onClick: (Session) -> Void
    
    var body: some View {
        List {
            Text("Select Schedules")
                .padding(.vertical)
                .listRowSeparator(.hidden)
            
            ForEach(sessions, id: \.id) { session in
                let isChecked = selectedSchedules.contains(session)
                
                Button {
                    onClick(session)
                } label: {
                    HStack {
                        Text("Session \(session.id)")
                        
                        Spacer()
                        
                        Text(":")
                        
                        Spacer()
                        
                        Text(session.time)
                        
                        Spacer()
                        
                        Image(systemName: isChecked ? "checkmark.square" : "square")
                    }
                    .contentShape(Rectangle())
                }
                .buttonStyle(.plain)
            }
        }
        .listStyle(.plain)
    }
}

struct SelectSchedulesSheet_Previews: PreviewProvider {
    static var previews: some View {
        SelectSchedulesSheet(
            selectedSchedules: [],
            onClick: { _ in }
        )
    }
}
