import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        AppModule()
        
        AuthNetworkModule()
        AuthRepositoryModule()
        
        UserNetworkModule()
        UserRepositoryModule()
        
        MentorNetworkModule()
        MentorRepositoryModule()
        
        ReviewNetworkModule()
        ReviewRepositoryModule()
        
        OrderNetworkModule()
        OrderRepositoryModule()
        
        InboxDatabaseModule()
        InboxRepositoryModule()
        MessageDatabaseModule()
        MessageRepositoryModule()
        ParticipantLocalModule()
        ParticipantRepositoryModule()
        
        ValidationUseCasesModule()
        AuthUseCasesModule()
        UserUseCasesModule()
        MentorUseCasesModule()
        OrderUseCasesModule()
        ReviewUseCasesModule()
        InboxUseCasesModule()
        MessageUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
