//
//  SessionAsUserScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SessionAsUserScreen: View {
    let sessionId: String
    let mentorId: String
    
    @StateObject private var viewModel = IosSessionAsUserViewModel()
    
    let converter = LocalDateConverter()
    
    var body: some View {
        ZStack {
            List {
                Section("Mentor Detail") {
                    SmallMentorCard(mentor: viewModel.state.mentor)
                }
                .listRowSeparator(.hidden)
                
                Section("Schedule") {
                    let dateString = converter.localDateToString(
                        date: viewModel.state.historySession?.date ?? converter.currentDate()
                    )
                    let timeString = viewModel.state.historySession?.schedule.time ?? ""
                    
                    Text("\(dateString), \(timeString)")
                }
                .listRowSeparator(.hidden)
                
                Section("Links") {
                    VStack(alignment: .leading) {
                        Text("Main Links")
                        
                        if let link = viewModel.state.sessionInfo?.mainLink {
                            Link(
                                link,
                                destination: URL(string: link)!
                            )
                            .font(.subheadline)
                            .foregroundColor(.blue)
                        } else {
                            Text("Loading...")
                                .font(.subheadline)
                        }
                    }
                    
                    VStack(alignment: .leading) {
                        Text("Back Up Links")
                        
                        if let link = viewModel.state.sessionInfo?.backupLink {
                            Link(
                                link,
                                destination: URL(string: link)!
                            )
                            .font(.subheadline)
                            .foregroundColor(.blue)
                        } else {
                            Text("Loading...")
                                .font(.subheadline)
                        }
                    }
                    
                    VStack(alignment: .leading) {
                        Text("Material Links")
                        
                        if let link = viewModel.state.sessionInfo?.materialLink {
                            Link(
                                link,
                                destination: URL(string: link)!
                            )
                            .font(.subheadline)
                            .foregroundColor(.blue)
                        } else {
                            Text("Loading...")
                                .font(.subheadline)
                        }
                    }
                }
                .listRowSeparator(.hidden)
                .buttonStyle(.plain)
                
                Section("Payment Method") {
                    HStack {
                        Image(viewModel.state.historySession?.paymentMethod.getLogo() ?? "ic_no_picture")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 50, height: 50)
                        
                        Spacer()
                            .frame(width: 32)
                        
                        Text(viewModel.state.historySession?.paymentMethod.name ?? "Loading...")
                    }
                }
                .listRowSeparator(.hidden)
                
                Divider()
                    .listRowSeparator(.hidden)
                
                Group {
                    HStack {
                        Text("Platform Fee")
                        
                        Spacer()
                        
                        Text("Rp. 0")
                    }
                    
                    HStack {
                        Text("Discount")
                        
                        Spacer()
                        
                        Text("- Rp. 0")
                    }
                    
                    HStack {
                        Text("Mentor Fee")
                        
                        Spacer()
                        
                        Text("Rp. \(viewModel.state.mentor?.price ?? "0")")
                    }
                    
                    Divider()
                    
                    HStack {
                        Text("Total Price")
                        
                        Spacer()
                        
                        Text("Rp. \(viewModel.state.historySession?.totalPrice ?? "0")")
                    }
                }
                .listRowSeparator(.hidden)
            }
            .listStyle(.plain)
            
            if viewModel.state.historySession?.status == "3" {
                VStack {
                    Spacer()
                    
                    HStack {
                        Spacer()
                        
                        NavigationLink {
                            Text("Review")
                        } label: {
                            Text("Review")
                                .font(.title2)
                                .foregroundColor(Color.white)
                                .padding()
                        }
                        .buttonStyle(.plain)
                        .background(.primary)
                        .cornerRadius(16)
                        .padding()
                        .shadow(
                            color: Color.black.opacity(0.3),
                            radius: 3,
                            x: 3,
                            y: 3
                        )
                    }
                }
            }
        }
        .navigationTitle("Session")
        .navigationBarTitleDisplayMode(.inline)
        .onAppear {
            viewModel.startObserving()
            viewModel.initSession(
                sessionId: sessionId,
                mentorId: mentorId
            )
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct SessionAsUserScreen_Previews: PreviewProvider {
    static var previews: some View {
        SessionAsUserScreen(
            sessionId: "H1",
            mentorId: "M1"
        )
    }
}
