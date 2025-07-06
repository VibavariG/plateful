Recipe Schema
id, UUID
name, String
description, String
createdAt, DateTime

Ingredient Schema
id, UUID
name, String
unit, String
brand, String
servingSize, Decimal
fiberPerServing, Decimal
proteinPerServing, Decimal
caloriesPerServing, Decimal
totalSugarsPerServing, Decimal
addedSugarsPerServing, Decimal
totalFatPerServing, Decimal
transFatPerServing,Decimal
saturatedFatPerServing, Decimal
carbsPerServing, Decimal
cholestrolPerServing, Decimal
sodiumPerServing, Decimal 
vitDPerServing, Decimal
calciumPerServing, Decimal
ironPerServing, Decimal
potassiumPerServing, Decimal
isSkinSafe, String
isGutSafe, String
createdAt, DateTime


RecipeIngredient Schema
id, UUID
recipeId, UUID
ingredientId, UUID
quantity, Decimal
unitOverride, String 
createdAt, DateTime