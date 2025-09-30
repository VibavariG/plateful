import { useState } from "react";

export default function IngredientForm() {
  const [form, setForm] = useState({
    name: "",
    unit: "",
    brand: "",
    servingSize: "",
    fiberPerServing: "",
    fiberUnit: "",
    proteinPerServing: "",
    proteinUnit: "",
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Submitting ingredient:", form);

    try {
      // Replace with your backend API URL
      const res = await fetch("http://localhost:8080/ingredients", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!res.ok) throw new Error("Failed to save ingredient");
      const data = await res.json();
      console.log("Saved:", data);
      alert("Ingredient saved successfully!");

      // reset form
      setForm({
        name: "",
        unit: "",
        brand: "",
        servingSize: "",
        fiberPerServing: "",
        fiberUnit: "",
        proteinPerServing: "",
        proteinUnit: "",
      });
    } catch (err) {
      console.error(err);
      alert("Error saving ingredient");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-3">
      {Object.keys(form).map((key) => (
        <div key={key}>
          <label className="block text-sm font-medium capitalize">
            {key}
          </label>
          <input
            type="text"
            name={key}
            value={form[key]}
            onChange={handleChange}
            className="w-full border rounded p-2"
          />
        </div>
      ))}
      <button
        type="submit"
        className="px-4 py-2 bg-blue-600 text-white rounded"
      >
        Save
      </button>
    </form>
  );
}
