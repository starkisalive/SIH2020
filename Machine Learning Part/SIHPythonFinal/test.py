count = int(input())
print(count)
if count>2 and count<7:
	lod = 'Modrate'
elif count >= 7 :
        lod = 'Major'
elif count>0 and count<3:
        lod = 'Minor'
else:
        lod = 'None'

print(lod)
