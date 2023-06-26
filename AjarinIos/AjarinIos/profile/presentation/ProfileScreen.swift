import SwiftUI
import shared

struct ProfileScreen: View {
    let isMentor: Bool = true
    
    @StateObject private var viewModel = IosProfileViewModel()
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        NavigationView {
            List {
                HStack {
                    ZStack {
                        Color.purple
                        
                        Text(
                            String(viewModel.state.user?.name.first ?? "?")
                        )
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
                        Text(viewModel.state.user?.name ?? "Name")
                            .font(.subheadline)
                        
                        Spacer()
                            .frame(height: 4)
                        
                        Text(viewModel.state.user?.email ?? "Email")
                            .foregroundColor(.gray)
                            .font(.caption)
                    }
                    
                    Spacer()
                }
                .padding()
                .background(
                    .purple
                        .opacity(0.2)
                )
                .listRowInsets(EdgeInsets())
                .listRowSeparator(.hidden)
                
                if !isMentor {
                    NavigationLink {
                        Text("Hello")
                    } label: {
                        HStack {
                            Spacer()
                            
                            Text("Apply as Mentor")
                                .fontWeight(.bold)
                                .font(.subheadline)
                            
                            Spacer()
                        }
                        .foregroundColor(.purple)
                    }
                    .padding()
                    .contentShape(Rectangle())
                    .overlay(
                        RoundedRectangle(cornerRadius: 6)
                            .stroke(.purple)
                    )
                    .listRowSeparator(.hidden)
                }
                
                if isMentor {
                    NavigationLink {
                        
                    } label: {
                        HStack {
                            Text("Rp. 0")
                            
                            Spacer()
                            
                            Text("WITHDRAW")
                                .fontWeight(.semibold)
                                .foregroundColor(.purple)
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
                    .listRowSeparator(.hidden)
                }
                
                Section("Personal") {
                    ProfileButton(
                        text: "Account",
                        iconName: "person.circle.fill",
                        onClick: {  }
                    )
                    .listRowInsets(
                        EdgeInsets(
                            top: 5,
                            leading: 20,
                            bottom: 5,
                            trailing: 20
                        )
                    )
                    
                    if isMentor {
                        NavigationLink {
                            BankAccountScreen()
                        } label: {
                            HStack {
                                Image(systemName: "person.circle.fill")
                                
                                Spacer()
                                    .frame(width: 16)
                                
                                Text("Bank Account")
                                    .fontWeight(.bold)
                                    .font(.subheadline)
                                
                                Spacer()
                            }
                        }
                        .padding()
                        .contentShape(Rectangle())
                        .overlay(
                            RoundedRectangle(cornerRadius: 6)
                                .stroke(.gray)
                        )
                        .listRowInsets(
                            EdgeInsets(
                                top: 5,
                                leading: 20,
                                bottom: 5,
                                trailing: 20
                            )
                        )
                    }
                }
                .listRowSeparator(.hidden)
                
                Section("General") {
                    ProfileButton(
                        text: "Settings",
                        iconName: "gearshape.fill",
                        onClick: {  }
                    )
                    .listRowInsets(
                        EdgeInsets(
                            top: 5,
                            leading: 20,
                            bottom: 5,
                            trailing: 20
                        )
                    )
                    
                    ProfileButton(
                        text: "Security",
                        iconName: "checkmark.shield.fill",
                        onClick: {  }
                    )
                    .listRowInsets(
                        EdgeInsets(
                            top: 5,
                            leading: 20,
                            bottom: 5,
                            trailing: 20
                        )
                    )
                    
                    ProfileButton(
                        text: "Terms and Conditions",
                        iconName: "doc.fill",
                        onClick: {  }
                    )
                    .listRowInsets(
                        EdgeInsets(
                            top: 5,
                            leading: 20,
                            bottom: 5,
                            trailing: 20
                        )
                    )
                    
                    ProfileButton(
                        text: "Help",
                        iconName: "questionmark.circle.fill",
                        onClick: {  }
                    )
                    .listRowInsets(
                        EdgeInsets(
                            top: 5,
                            leading: 20,
                            bottom: 5,
                            trailing: 20
                        )
                    )
                    
                    ProfileButton(
                        text: "About",
                        iconName: "info.circle.fill",
                        onClick: {  }
                    )
                    .listRowInsets(
                        EdgeInsets(
                            top: 5,
                            leading: 20,
                            bottom: 5,
                            trailing: 20
                        )
                    )
                }
                .listRowSeparator(.hidden)
                
                ProfileButton(
                    text: "Log Out",
                    iconName: "rectangle.portrait.and.arrow.right",
                    onClick: {
                        viewModel.onEvent(
                            event: .ToggleDialog(
                                isShow: true
                            )
                        )
                    }
                )
                .listRowSeparator(.hidden)
                .foregroundColor(.red)
                .padding(.top, 16)
            }
            .listStyle(.plain)
            .confirmationDialog(
                "Are you sure to log out?",
                isPresented: Binding(
                    get: {
                        viewModel.state.isDialogShow
                    },
                    set: {
                        viewModel.onEvent(
                            event: .ToggleDialog(isShow: $0)
                        )
                    }
                )
            ) {
                Button("Log Out", role: .destructive) {
                    viewModel.onEvent(
                        event: .LogOut()
                    )
                }
            } message: {
                Text("You cannot undo this action")
            }
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
        }
    }
}

struct ProfileScreen_Previews: PreviewProvider {
    static var previews: some View {
        ProfileScreen()
    }
}
