import { BrowserRouter, Route, Routes} from "react-router-dom";
import { DataProvider } from "./context/DataContext";
import Home from "./pages/Home/Home";
import Profile from "./pages/Profile/Profile";
import RestaurantDetail from "./pages/RestaurantDetail/RestaurantDetail";
import "./App.css";
import SearchBar from "./components/SearchBar.tsx"
import Filter from "./pages/Filter/Filter.tsx"

function App() {

    return (
        <div className="w-full h-[100%]">
            <BrowserRouter>
                <DataProvider>
                    <Routes>
                        <Route
                            exact
                            path="restaurants/filter"
                            element={
                            <Filter />
                            }
                        />
                        <Route
                            exact
                            path="/"
                            element={
                                <SearchBar />
                            }
                        />
                        <Route
                            exact
                            path="/home"
                            element={
                                <Home />
                            }
                        />
                        <Route
                            exact
                            path="/profile"
                            element={
                                <Profile />
                            }
                        />
                        <Route
                            exact
                            path={`/restaurant/:id`}
                            element={
                                <RestaurantDetail />
                            }
                        />
                    </Routes>
               </DataProvider>
            </BrowserRouter>
        </div>
    );
}

export default App;
