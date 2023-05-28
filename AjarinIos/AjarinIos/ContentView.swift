import SwiftUI
import shared

struct ContentView: View {
    @AppStorage("isLogin") private var isLogin: Bool = false
    @State private var selectedTab: MainTab = .Home
    
    let appearance: UITabBarAppearance = UITabBarAppearance()
    init() {
        UITabBar.appearance().scrollEdgeAppearance = appearance
    }
    
	var body: some View {
        if isLogin {
            TabView(selection: $selectedTab) {
                HomeScreen()
                    .tabItem {
                        Label(
                            "Home",
                            systemImage: "house.fill"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(MainTab.Home)
                
                SearchMentorScreen()
                    .tabItem {
                        Label(
                            "Search",
                            systemImage: "magnifyingglass"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(MainTab.Search)
                
                Text("History")
                    .tabItem {
                        Label(
                            "History",
                            systemImage: "clock.arrow.circlepath"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(MainTab.History)
                
                ProfileScreen()
                    .tabItem {
                        Label(
                            "Profile",
                            systemImage: "person.fill"
                        )
                        .labelStyle(.iconOnly)
                    }
                    .tag(MainTab.Profile)
            }
        } else {
            LoginScreen()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
