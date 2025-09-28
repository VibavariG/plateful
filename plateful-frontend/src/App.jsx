import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import IngredientsPage from "./pages/IngredientsPage";

export default function App() {
  return (
    <Router>
      <div className="p-4 max-w-4xl mx-auto">
        <nav className="mb-4">
          <Link to="/ingredients" className="text-blue-600 font-medium">Ingredients</Link>
        </nav>
        <Routes>
          <Route path="/ingredients" element={<IngredientsPage />} />
        </Routes>
      </div>
    </Router>
  );
}
