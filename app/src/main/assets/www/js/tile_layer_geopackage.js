L.TileLayer.GeoPackage = L.TileLayer.extend({
	// database
	dbTiles: null,

	initialize: function(db, options) {
		L.Util.setOptions(this, options);
		this.dbTiles = db;
	},

	getTileUrl: function(tilePoint, tile) {

		this.dbTiles.getTiles(tilePoint.x,tilePoint.y,tilePoint.z).then(function (tiles) {
				alert(JSON.stringify(tiles));
				var items = angular.copy(tiles);
				tile.src = 'data:image/png;base64,' + items[0].tile_data;

			});
	},

	_loadTile: function(tile, tilePoint) {
		tile._layer = this;
		tile.onload = this._tileOnLoad;
		tile.onerror = this._tileOnError;
		this._adjustTilePoint(tilePoint);
		this.getTileUrl(tilePoint, tile);
	}
});