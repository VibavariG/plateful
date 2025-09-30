import { useEffect, useState } from "react";
import api from "../api";

export default function IngredientList() {
  const [names, setNames] = useState([]);
  useEffect(() => {
    api.get("/ingredients/names").then(res => setNames(res.data.data || []));
  }, []);
  return (
    <div className="grid gap-2">
      {names.map((n, i) => <div key={i} className="p-2 border rounded">{n}</div>)}
    </div>
  );
}
