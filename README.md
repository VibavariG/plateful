# Plateful

A personal recipe dashboard — publish recipes with a photo, browse them by
Dessert / Snacks / Meals, and search by name or ingredient.

Recipes are saved in your browser's local storage, scoped to wherever this
page is hosted. Once it's live on GitHub Pages, saves are permanent for that
URL in that browser. Use the backup/restore buttons in the app to move your
data between browsers or devices.

## Deploy to GitHub Pages (no command line needed)

1. Go to https://github.com/new and create a new **public** repository
   (e.g. `recipe-box`).
2. On the repo page, click **Add file → Upload files**, and drag in
   `index.html` from this folder.
3. Commit the file (straight to the `main` branch is fine).
4. Go to **Settings → Pages** in the repo.
5. Under "Build and deployment", set **Source** to `Deploy from a branch`,
   branch `main`, folder `/ (root)`. Save.
6. Wait ~1 minute, then refresh — GitHub shows the live URL at the top of
   that Pages settings screen. It'll look like:
   `https://<your-username>.github.io/recipe-box/`

That's it — open that URL, add a recipe, refresh the page, and it'll still
be there.

## Deploy via git (if you'd rather use the terminal)

```bash
cd recipe-box-repo
git init
git add index.html README.md
git commit -m "Add recipe box"
git branch -M main
git remote add origin https://github.com/<your-username>/recipe-box.git
git push -u origin main
```

Then enable Pages the same way as steps 4–6 above.