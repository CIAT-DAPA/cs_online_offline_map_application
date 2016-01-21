L.TileLayer.GeoPackage = L.TileLayer.extend({

	initialize: function(options) {
    		L.Util.setOptions(this, options);
    	},

	getTileUrl: function(tilePoint, tile) {

		var tiles=getTiles(tilePoint.x,tilePoint.y,tilePoint.z);
		tile.src = 'data:image/png;base64,' + tiles;

	},

	_loadTile: function(tile, tilePoint) {
		tile._layer = this;
		tile.onload = this._tileOnLoad;
		tile.onerror = this._tileOnError;
		this._adjustTilePoint(tilePoint);
		this.getTileUrl(tilePoint, tile);
	}
});