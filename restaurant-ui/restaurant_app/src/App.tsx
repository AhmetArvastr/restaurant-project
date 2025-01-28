import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import { useContext } from "react";
import { AuthProvider, AuthContext } from "./context/AuthContext";
import { DataProvider } from "./context/DataContext";
import Auth from "./pages/Auth/Auth";
import Home from "./pages/Home/Home";
import Profile from "./pages/Profile/Profile";
import RestaurantDetail from "./pages/RestaurantDetail/RestaurantDetail";
import "./App.css";

function App() {
    const authContext = useContext(AuthContext);

    const jwt = authContext ? authContext.jwt : "";
    console.log("jwt", jwt);

    localStorage.setItem("username", "admin");
    localStorage.setItem("password", "admin");

    return (
        <div className="w-full h-[100%]">
            <BrowserRouter>
                <AuthProvider>
                    <DataProvider>
                        <Routes>
                            <Route
                                exact
                                path="/"
                                element={
                                    jwt === "" || jwt === null ? (
                                        <Auth />
                                    ) : (
                                        <Navigate to="/home" />
                                    )
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
                </AuthProvider>
            </BrowserRouter>
        </div>
    );
}

export default App;
