//
//  SessionAsMentorScreen.swift
//  AjarinIos
//
//  Created by Darren Thiores on 23/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SessionAsMentorScreen: View {
    let sessionId: String
    let userId: String
    
    @StateObject private var viewModel = IosSessionAsMentorViewModel()
    
    let converter = LocalDateConverter()
    
    var body: some View {
        ZStack {
            List {
                Section("User Detail") {
                    SmallUserCard(
                        user: viewModel.state.user,
                        course: viewModel.state.historySession?.course
                    )
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
                
                Divider()
                    .listRowSeparator(.hidden)
                
                Section {
                    VStack(alignment: .leading) {
                        Text("Main Links")
                        
                        if viewModel.state.isSessionInfoEditable {
                            DefaultTextField(
                                title: viewModel.state.mainLinkText,
                                text: Binding(
                                    get: {
                                        viewModel.state.mainLinkText
                                    },
                                    set: {
                                        viewModel.onEvent(
                                            event: .OnMainLinkTextChange(
                                                newLink: $0
                                            )
                                        )
                                    }
                                )
                            )
                        } else {
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
                    }
                    
                    VStack(alignment: .leading) {
                        Text("Back Up Links")
                        
                        if viewModel.state.isSessionInfoEditable {
                            DefaultTextField(
                                title: viewModel.state.backupLinkText,
                                text: Binding(
                                    get: {
                                        viewModel.state.backupLinkText
                                    },
                                    set: {
                                        viewModel.onEvent(
                                            event: .OnBackupLinkTextChange(
                                                newLink: $0
                                            )
                                        )
                                    }
                                )
                            )
                        } else {
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
                    }
                    
                    VStack(alignment: .leading) {
                        Text("Material Links")
                        
                        if viewModel.state.isSessionInfoEditable {
                            DefaultTextField(
                                title: viewModel.state.materialLinkText,
                                text: Binding(
                                    get: {
                                        viewModel.state.materialLinkText
                                    },
                                    set: {
                                        viewModel.onEvent(
                                            event: .OnMaterialLinkTextChange(
                                                newLink: $0
                                            )
                                        )
                                    }
                                )
                            )
                        } else {
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
                } header: {
                    HStack {
                        Text("Links")
                        
                        Spacer()
                        
                        Button {
                            viewModel.onEvent(
                                event: .OnToggleEditSessionInfo()
                            )
                        } label: {
                            Text(
                                viewModel.state.isSessionInfoEditable ? "Save" : "Edit"
                            )
                            .foregroundColor(.purple)
                        }
                        .buttonStyle(.plain)
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
                        
                        Text("Rp. \(viewModel.state.historySession?.mentorPrice ?? "0")")
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
            
            if viewModel.state.historySession?.status == "1" {
                VStack {
                    Spacer()
                    
                    HStack {
                        Spacer()
                        
                        Button {
                            viewModel.onEvent(
                                event: .OnStartClass()
                            )
                        } label: {
                            Text("Start")
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
            
            if viewModel.state.historySession?.status == "2" {
                VStack {
                    Spacer()
                    
                    HStack {
                        Spacer()
                        
                        Button {
                            viewModel.onEvent(
                                event: .OnFinishClass()
                            )
                        } label: {
                            Text("Finish")
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
                userId: userId
            )
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct SessionAsMentorScreen_Previews: PreviewProvider {
    static var previews: some View {
        SessionAsMentorScreen(
            sessionId: "H1",
            userId: "U1"
        )
    }
}
